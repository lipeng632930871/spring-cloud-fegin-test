package com.springcloud.fegin.test.example.event;

public interface Event {

    EventType getType();

    Long getId();
}
