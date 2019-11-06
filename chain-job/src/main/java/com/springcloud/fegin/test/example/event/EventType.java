package com.springcloud.fegin.test.example.event;

import java.util.HashMap;
import java.util.Map;

public enum EventType {

    CXC_SITE_CLOSE_REFUND("cxc_site_close_refund","test app");
    private String code;
    private String msg;
    private static Map<String, EventType> hashMap = new HashMap();
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    EventType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static EventType get(String code) {
        if (code == null) {
            return null;
        } else {
            if (hashMap.isEmpty()) {
                EventType[] eventTypes = values();
                int length = eventTypes.length;

                for(int i = 0; i < length; ++i) {
                    EventType executeStateEnum = eventTypes[i];
                    hashMap.put(executeStateEnum.getCode(), executeStateEnum);
                }
            }

            return (EventType)hashMap.get(code);
        }
    }

    public static void main(String[] args) {
        EventType eventType = EventType.get("cxc_site_close_refund");
        System.out.println(eventType.getCode());
    }
}
