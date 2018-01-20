package com.demo.wechatint.wechatintegration.dataobject;

public class GenericResponseJson {

    private String responseType;

    private String responseStatus;

    private String responseMessage;

    public GenericResponseJson(String responseType, String responseStatus, String responseMessage) {
        this.responseType = responseType;
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
