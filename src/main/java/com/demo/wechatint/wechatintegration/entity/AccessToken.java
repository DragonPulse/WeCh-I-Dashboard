package com.demo.wechatint.wechatintegration.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;


@Document(collection = "accesstoken")
public class AccessToken extends AbstractDocument {

    //@Indexed(unique = false)
    private String accessToken;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date expiryDate;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

}

