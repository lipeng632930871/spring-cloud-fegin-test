package com.springcloud.fegin.test.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("/testApp")
    public String testApp() {
        System.out.println("testApp");
        return "Hello world testApp!";
    }
}
