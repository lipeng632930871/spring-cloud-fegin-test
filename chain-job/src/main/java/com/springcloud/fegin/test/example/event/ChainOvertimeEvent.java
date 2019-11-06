package com.springcloud.fegin.test.example.event;


import lombok.Data;

import java.util.Date;

/**
 * Description: event
 *
 * @author 003186
 */
@Data
public class ChainOvertimeEvent implements Event {

    private Long id;
    private String overtimeTaskBusinessCode;

    private String eventType;

    private Date endTime;

    @Override
    public EventType getType() {
        return EventType.get(this.getEventType());
    }
}
