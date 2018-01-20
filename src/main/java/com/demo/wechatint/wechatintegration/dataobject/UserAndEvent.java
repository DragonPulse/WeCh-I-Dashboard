package com.demo.wechatint.wechatintegration.dataobject;

import java.util.Date;

public class UserAndEvent {

    private String openId;

    private String eventType;

    private Date createTime;

    public UserAndEvent(String openId,String eventType){
        this.eventType = eventType;
        this.openId=openId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getCreateTime() { return createTime; }

    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
