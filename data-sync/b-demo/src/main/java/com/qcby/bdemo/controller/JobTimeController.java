package com.qcby.bdemo.controller;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qcby.bdemo.po.User;
import com.qcby.bdemo.po.User2;
import com.qcby.bdemo.service.User2Service;
import com.qcby.bdemo.service.UserService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JobTimeController {

    @Resource
    private UserService userService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private Gson gson = new GsonBuilder().create();

    @Resource
    private User2Service user2Service;

    @Resource
    private RedisTemplate<String,HashMap<String,String>> redisTemplate;
    /**
     * 高并发的时候采用Xxjob作为定时任务
     * @return
     */
    @XxlJob("aaaaaa")
    public void demo(){
        XxlJobHelper.log("输出日志");
        //将b数据库存入到redis
        LambdaUpdateWrapper<User2> lamp = new LambdaUpdateWrapper<>();
        lamp.lt(User2::getId,20);
        List<User2> user2s = user2Service.list(lamp);
        //先将b数据库中的数据装进hashmap中作为value
        HashMap<String,String> hashMap = new HashMap<>();
        for (User2 user2 : user2s) {
            String  key = String.valueOf(user2.getId());
            hashMap.put(key,gson.toJson(user2));
        }
        redisTemplate.opsForHash().putAll("user",hashMap);
        redisTemplate.expire("user", 5, TimeUnit.MINUTES);

        //参数
        System.out.println(XxlJobHelper.getJobParam());
        //一共有多少台机器
        int shardTotal = XxlJobHelper.getShardTotal();
        //现在是第几台机器
        int shardIndex = XxlJobHelper.getShardIndex();
        //从a数据库中读取数据
        List<User> users = userService.getUsersByShard(shardTotal, shardIndex);
        //将数据放入到kafka
        for (User user : users) {
            User message = user;
            kafkaTemplate.send("testtopic", gson.toJson(message));
        }
    }
}
