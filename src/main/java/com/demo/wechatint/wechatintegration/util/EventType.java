package com.demo.wechatint.wechatintegration.util;

public enum EventType {

    SUBSCRIBE("subscribe"),
    UNSUBSCRIBE("unsubscribe");

    private String eventType;

    private EventType(String eventType){
        this.eventType=eventType;
    }
}
