package com.demo.wechatint.wechatintegration.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.wechatint.wechatintegration.config.JsonDateSerializer;
import com.fasterxml.jackson.annotation.*;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "subscriberinfo")
public class SubscriberInfo extends AbstractDocument{



    private Integer subscribe;

    @Indexed(unique =  true)
    private String openid;

    private String nickname;

    private Integer sex;

    private String language;

    private String city;

    private String province;

    private String country;

    private String headimgurl;

    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using=JsonDateSerializer.class)
    private Date createTime;

    private String remark;

    private Integer groupid;

    private List<Object> tagidList = null;

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public List<Object> getTagidList() {
        return tagidList;
    }

    public void setTagidList(List<Object> tagidList) {
        this.tagidList = tagidList;
    }
}
