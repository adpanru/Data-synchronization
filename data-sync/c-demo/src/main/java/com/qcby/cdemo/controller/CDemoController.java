package com.qcby.cdemo.controller;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * cdemo控制器
 *
 * @author 小如
 * @date 2023/07/28
 */
@Component
public class CDemoController {

    @Resource
    private RedisTemplate redisTemplate;

    @XxlJob("cccccc")
    public void demo(){
        //参数
        System.out.println(XxlJobHelper.getJobParam());
        //拿到redis中的数据并转化成user对象
        HashMap<String,String> userMap = (HashMap<String, String>) redisTemplate.opsForHash().entries("user");
        List<String> deleteList = new ArrayList<>();
        for (Map.Entry<String, String> entry : userMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            deleteList.add(value);
            redisTemplate.opsForHash().delete("user",key);
        }
        redisTemplate.opsForList().rightPushAll("delete", deleteList);
    }
}
