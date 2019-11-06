package com.springcloud.fegin.test.example.eventframework;

import com.springcloud.fegin.test.example.event.ChainOvertimeEvent;
import com.springcloud.fegin.test.example.event.Event;
import com.springcloud.fegin.test.example.event.EventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.EnumMap;

@Slf4j
@Component
public class EventDispatcher {
    private EnumMap<EventType,Handler<? extends Event>> handlerEnumMap = null;

    public EventDispatcher() {
        this.handlerEnumMap = new EnumMap<EventType, Handler<? extends Event>>(EventType.class);
    }

    public <E extends Event> void registerHandler(EventType type,Handler<E> handler){
        handlerEnumMap.put(type, handler);
    }

    public <E extends Event> void dispatch(E event){
        Handler<E> handler = (Handler<E>) handlerEnumMap.get(event.getType());
        if (handler != null) {
            handler.handleEvent(event);
        } else {
            if (event instanceof ChainOvertimeEvent) {
                ChainOvertimeEvent chainOvertimeEvent = (ChainOvertimeEvent) event;
                log.error("EventDispatcher can not found the event handle:{}",chainOvertimeEvent.getEventType());
            }
        }
    }
}
