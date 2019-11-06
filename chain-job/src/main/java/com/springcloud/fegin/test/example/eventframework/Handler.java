package com.springcloud.fegin.test.example.eventframework;


import com.springcloud.fegin.test.example.event.Event;

/**
 * Description: event handler
 *
 * @author 003186
 */
public interface Handler<E extends Event> {

    public void handleEvent(E event);
}
