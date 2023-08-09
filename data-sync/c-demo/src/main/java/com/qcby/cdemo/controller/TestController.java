package com.qcby.cdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-c")
public class TestController {
    @GetMapping("/c")
    public String hello(){
        return "success";
    }
}
