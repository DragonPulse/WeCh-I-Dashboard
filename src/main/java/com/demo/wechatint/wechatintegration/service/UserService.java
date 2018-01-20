package com.demo.wechatint.wechatintegration.service;

import com.demo.wechatint.wechatintegration.dataobject.SubscriberSummary;
import com.demo.wechatint.wechatintegration.dataobject.UserDetailResponse;
import com.demo.wechatint.wechatintegration.dataobject.UserResponse;
import com.demo.wechatint.wechatintegration.entity.SubscriberInfo;

import java.util.List;
import java.util.Map;

public interface UserService {

    public List<String> getAllOpenIds() throws Exception;

    public UserDetailResponse getUserInfo(String openId) throws Exception;

    public List<UserDetailResponse> getAllUserInfo() throws Exception;

    public UserResponse getUserOpenIds();

    public SubscriberInfo saveSubscriberInfo(SubscriberInfo subscriberInfo);

    public void saveSubscriberInfoList(List<SubscriberInfo> subscriberInfoList);

    public Map<String,String> getSubNameOpenIdMap();

    public List<SubscriberSummary> getSubscriberSummary(String key);

}
