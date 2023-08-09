package com.qcby.ddemo.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qcby.ddemo.po.User;
import com.qcby.ddemo.service.UserService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class DDemoController {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private Gson gson = new GsonBuilder().create();
    @Resource
    private UserService userService;

    @XxlJob("dddddd")
    public void insertList() {
        //参数
        System.out.println(XxlJobHelper.getJobParam());

        //批量新增
        List<String> insertList = redisTemplate.opsForList().range("insert", 0, -1);
        if (insertList != null && !insertList.isEmpty()) {
            //将json转换成user类
            List<User> userList = new ArrayList<>();
            for (String json : insertList) {
                User user = gson.fromJson(json, User.class);
                userList.add(user);
            }
            //批量插入
            if(userService.saveBatch(userList)){
                // 删除整个列表
                redisTemplate.opsForList().trim("insert", insertList.size(), -1);
            }
        }

        //批量修改
        List<String> updateList = redisTemplate.opsForList().range("update", 0, -1);
        if (updateList != null && !updateList.isEmpty()) {
            //将json转换成user类
            List<User> updateUserList = new ArrayList<>();
            for (String json : updateList) {
                User user = gson.fromJson(json, User.class);
                updateUserList.add(user);
            }
            //批量插入
            if(userService.updateBatchById(updateUserList)){
                // 删除整个列表
                redisTemplate.opsForList().trim("update", updateList.size(), -1);
            }
        }
        //批量删除
        List<String> deleteList = redisTemplate.opsForList().range("delete",0,-1);

        if(deleteList != null && !deleteList.isEmpty()){
            List<Long> delUserList = new LinkedList<>();
            for (String s : deleteList) {
                User user = gson.fromJson(s,User.class);
                delUserList.add(user.getId());
            }
            if(userService.removeBatchByIds(delUserList)){
                redisTemplate.opsForList().trim("delete",deleteList.size(),-1);
            }
        }
    }
}
