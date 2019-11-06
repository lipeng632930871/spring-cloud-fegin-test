package com.springcloud.fegin.test.example.eventframework;

import com.springcloud.fegin.test.example.event.EventType;
import com.springcloud.fegin.test.example.eventhandler.AutoCloseOrderCxcHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitRegisterHandlersProcessor implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private EventDispatcher eventDispatcher;

    @Autowired
    private AutoCloseOrderCxcHandler autoCloseOrderCxcHandler;

    @Autowired
    private EventServiceExecutor eventServiceExecutor;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        eventDispatcher.registerHandler(EventType.CXC_SITE_CLOSE_REFUND,autoCloseOrderCxcHandler);
        eventServiceExecutor.startExecute();
    }
}
