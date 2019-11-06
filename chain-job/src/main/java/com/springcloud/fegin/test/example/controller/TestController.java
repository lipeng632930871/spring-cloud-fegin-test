package com.springcloud.fegin.test.example.controller;

import com.springcloud.fegin.test.example.event.ChainOvertimeEvent;
import com.springcloud.fegin.test.example.event.Event;
import com.springcloud.fegin.test.example.event.EventType;
import com.springcloud.fegin.test.example.eventframework.EventQueue;
import com.springcloud.fegin.test.example.feign.TestFeginClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private TestFeginClient testFeginClient;

    @GetMapping("/testJob")
    public String testApp() {
        String s = testFeginClient.testApp();
        System.out.println(s);
        System.out.println("testJob");
        return "Hello world testJob!";
    }

    @GetMapping("/testCollect")
    public void testCollect(){
        ChainOvertimeEvent event = new ChainOvertimeEvent();
        event.setId(1L);
        event.setEndTime(new Date());
        event.setEventType(EventType.CXC_SITE_CLOSE_REFUND.getCode());
        event.setOvertimeTaskBusinessCode("0001");
        EventQueue.submitByRound(event);
    }
}
