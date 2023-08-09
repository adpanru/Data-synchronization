package com.qcby.ddemo.controller;

import com.qcby.ddemo.po.User;
import com.qcby.ddemo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/d")
public class TestController {

    @Resource
    private UserService userService;
    @GetMapping("/hello")
    public String aa(){
        User user = new User();
        user.setAge(1);
        user.setUsername("zhangsan");
        user.setBirthday("7,27");
        userService.save(user);
        return "success";
    }
}
