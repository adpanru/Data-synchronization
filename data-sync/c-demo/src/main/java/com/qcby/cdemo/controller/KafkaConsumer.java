package com.qcby.cdemo.controller;


import com.google.gson.GsonBuilder;
import com.qcby.cdemo.po.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.google.gson.Gson;
@Component
public class KafkaConsumer {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private Gson gson = new GsonBuilder().create();

    @KafkaListener(topics = {"testtopic"},groupId = "default-group")
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            //拿到kafka中的数据并且转化成user对象
            String message = (String) kafkaMessage.get();
            User user = gson.fromJson(message, User.class);

            //拿到redis中的数据并转化成user对象
            HashMap<String,String> userMap = (HashMap<String, String>) redisTemplate.opsForHash().entries("user");
            String value = (String) userMap.get(String.valueOf(user.getId()));

            if(value != null){
                User redisUser = gson.fromJson(value, User.class);
                if(!redisUser.equals(user)){
                    redisTemplate.opsForList().rightPush("update",message);
                    System.out.println("++++++++++++++++++++修改"+user.getId()+" "+user+" "+redisUser);
                    redisTemplate.opsForHash().delete("user",String.valueOf(user.getId()));
                }else {
                    redisTemplate.opsForHash().delete("user",String.valueOf(user.getId()));
                    System.out.println("====================未变动"+user.getId());
                }
            }else {
                redisTemplate.opsForList().rightPush("insert",message);
                System.out.println("-------------------新增"+user.getId());
                redisTemplate.opsForHash().delete("user",String.valueOf(user.getId()));
            }
        }
    }

}