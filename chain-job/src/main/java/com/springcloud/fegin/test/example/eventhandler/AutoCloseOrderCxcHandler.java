package com.springcloud.fegin.test.example.eventhandler;

import com.springcloud.fegin.test.example.event.ChainOvertimeEvent;
import com.springcloud.fegin.test.example.eventframework.Handler;
import com.springcloud.fegin.test.example.feign.TestFeginClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AutoCloseOrderCxcHandler implements Handler<ChainOvertimeEvent> {



//    @Autowired
//    TestFeginClient testFeginClient;

    @Override
    public void handleEvent(ChainOvertimeEvent event) {
        try {
//            String s = testFeginClient.testApp();
//            log.info("testApp:{}", s);
            log.info("test:{}", event);
        } catch (Exception e) {
        }
    }


}
