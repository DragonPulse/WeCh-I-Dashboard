package com.demo.wechatint.wechatintegration.dataobject;

import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "total",
        "count",
        "data",
        "next_openid"
})
public class UserResponse {

    @JsonProperty("total")
    private long total;
    @JsonProperty("count")
    private long count;
    @JsonProperty("data")
    private UserDataResponse data;
    @JsonProperty("next_openid")
    private String nextOpenid;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("total")
    public long getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(long total) {
        this.total = total;
    }

    @JsonProperty("count")
    public long getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(long count) {
        this.count = count;
    }

    @JsonProperty("data")
    public UserDataResponse getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(UserDataResponse data) {
        this.data = data;
    }

    @JsonProperty("next_openid")
    public String getNextOpenid() {
        return nextOpenid;
    }

    @JsonProperty("next_openid")
    public void setNextOpenid(String nextOpenid) {
        this.nextOpenid = nextOpenid;
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