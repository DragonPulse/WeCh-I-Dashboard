package com.demo.wechatint.wechatintegration.dataobject;


import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "errcode",
        "errmsg"
})
public class MessageResponse {

    @JsonProperty("errcode")
    private Integer errcode;
    @JsonProperty("errmsg")
    private String errmsg;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("errcode")
    public Integer getErrcode() {
        return errcode;
    }

    @JsonProperty("errcode")
    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    @JsonProperty("errmsg")
    public String getErrmsg() {
        return errmsg;
    }

    @JsonProperty("errmsg")
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
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