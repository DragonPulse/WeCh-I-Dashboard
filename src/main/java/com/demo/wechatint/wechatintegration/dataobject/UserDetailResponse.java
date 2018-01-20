package com.demo.wechatint.wechatintegration.dataobject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "subscribe",
        "openid",
        "nickname",
        "sex",
        "language",
        "city",
        "province",
        "country",
        "headimgurl",
        "subscribe_time",
        "remark",
        "groupid",
        "tagid_list"
})
public class UserDetailResponse {

    @JsonProperty("subscribe")
    private Integer subscribe;
    @JsonProperty("openid")
    private String openid;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("sex")
    private Integer sex;
    @JsonProperty("language")
    private String language;
    @JsonProperty("city")
    private String city;
    @JsonProperty("province")
    private String province;
    @JsonProperty("country")
    private String country;
    @JsonProperty("headimgurl")
    private String headimgurl;
    @JsonProperty("subscribe_time")
    private Integer subscribeTime;
    @JsonProperty("remark")
    private String remark;
    @JsonProperty("groupid")
    private Integer groupid;
    @JsonProperty("tagid_list")
    private List<Object> tagidList = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("subscribe")
    public Integer getSubscribe() {
        return subscribe;
    }

    @JsonProperty("subscribe")
    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    @JsonProperty("openid")
    public String getOpenid() {
        return openid;
    }

    @JsonProperty("openid")
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @JsonProperty("nickname")
    public String getNickname() {
        return nickname;
    }

    @JsonProperty("nickname")
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @JsonProperty("sex")
    public Integer getSex() {
        return sex;
    }

    @JsonProperty("sex")
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("province")
    public String getProvince() {
        return province;
    }

    @JsonProperty("province")
    public void setProvince(String province) {
        this.province = province;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("headimgurl")
    public String getHeadimgurl() {
        return headimgurl;
    }

    @JsonProperty("headimgurl")
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    @JsonProperty("subscribe_time")
    public Integer getSubscribeTime() {
        return subscribeTime;
    }

    @JsonProperty("subscribe_time")
    public void setSubscribeTime(Integer subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    @JsonProperty("remark")
    public String getRemark() {
        return remark;
    }

    @JsonProperty("remark")
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @JsonProperty("groupid")
    public Integer getGroupid() {
        return groupid;
    }

    @JsonProperty("groupid")
    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    @JsonProperty("tagid_list")
    public List<Object> getTagidList() {
        return tagidList;
    }

    @JsonProperty("tagid_list")
    public void setTagidList(List<Object> tagidList) {
        this.tagidList = tagidList;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}