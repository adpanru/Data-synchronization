package com.qcby.bdemo.controller;

import com.qcby.bdemo.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test11")
public class TestController {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserService userService;

    @GetMapping("/a")
    public String test(){
        System.out.println("..............");
        System.out.println(userService.count());
        return "hello";
    }

    @GetMapping("/savemsg")
    public String savemsg(){
        System.out.println("Redis>>>Set测试>>>");
        redisTemplate.opsForValue().set("NAME","张三");
        //获取保存的值
        System.out.println("获取到的NAME>>>" + redisTemplate.opsForValue().get("NAME"));
        return redisTemplate.opsForValue().get("NAME").toString();
    }

}
