package com.demo.wechatint.wechatintegration.service;

import com.demo.wechatint.wechatintegration.dataobject.SubscriberSummary;
import com.demo.wechatint.wechatintegration.dataobject.UserDetailResponse;
import com.demo.wechatint.wechatintegration.dataobject.UserResponse;
import com.demo.wechatint.wechatintegration.entity.AccessToken;
import com.demo.wechatint.wechatintegration.entity.SubscriberInfo;
import com.demo.wechatint.wechatintegration.repository.SubscriberInfoCustomRepository;
import com.demo.wechatint.wechatintegration.util.ServerUtility;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl  implements UserService{

    @Autowired
    ServerUtility serverUtility;

    @Autowired
    AccessTokenService accessTokenService;

    @Autowired
    SubscriberInfoCustomRepository subscriberInfoCustomRepository;


    @Override
    public Map<String,String> getSubNameOpenIdMap(){
        Map<String,String> subInfoOpenIdMap = new HashMap<>();
        List<SubscriberInfo> listOfSubscriber = subscriberInfoCustomRepository.getAllSubscriberInfo();
        for(SubscriberInfo subscriberInfo : listOfSubscriber){
            subInfoOpenIdMap.put(subscriberInfo.getOpenid(),subscriberInfo.getNickname());
        }
        return subInfoOpenIdMap;
    }

    public UserResponse getUserOpenIds(){
        Map<String,Object> paramMap = new HashMap<>();
        AccessToken accessToken = accessTokenService.getLatestAccessToken();
        paramMap.put("access_token",accessToken.getAccessToken());
        UserResponse userResponse = serverUtility.getUserOpenIds(paramMap);
        return userResponse;
    }

    @Override
    public List<String> getAllOpenIds() throws Exception {
        List<String> openIdsList = new ArrayList<>();
        List<SubscriberInfo> listOfSubscriber = subscriberInfoCustomRepository.findAllOpenIds();
        if(listOfSubscriber.size()==0){
            UserResponse userResponse = getUserOpenIds();
            if(userResponse.getData() != null && userResponse.getData().getOpenid()!=null) {
                openIdsList = userResponse.getData().getOpenid();
            }
        }else{
            for(SubscriberInfo subscriberInfo : listOfSubscriber){
                openIdsList.add(subscriberInfo.getOpenid());
            }
        }
        return openIdsList;
    }

    @Override
    @Cacheable(value="userDetails")
    public UserDetailResponse getUserInfo(String openId) throws Exception {
        AccessToken accessToken = accessTokenService.getLatestAccessToken();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("access_token",accessToken.getAccessToken());
        paramMap.put("openid",openId);
        paramMap.put("lang","en_US");
        UserDetailResponse userDetailResponse = serverUtility.getUserInfoByOpenId(paramMap);
        return userDetailResponse;
    }

    @Override
    @CachePut(value="userDetails")
    public List<UserDetailResponse> getAllUserInfo() throws Exception {
        List<UserDetailResponse> userDetailResponseList = new ArrayList<>();
        List<SubscriberInfo> subscriberInfoList = subscriberInfoCustomRepository.getAllSubscriberInfo();
        if(subscriberInfoList.size()>0){
            for(SubscriberInfo subscriberInfo:subscriberInfoList) {
                UserDetailResponse userDetailResponse = new UserDetailResponse();
                BeanUtils.copyProperties(subscriberInfo, userDetailResponse);
                userDetailResponseList.add(userDetailResponse);
            }
        }else{
            for (String openId: getAllOpenIds()) {
                UserDetailResponse userDetailResponse = getUserInfo(openId);
                userDetailResponseList.add(userDetailResponse);
                SubscriberInfo subscriberInfo = new SubscriberInfo();
                BeanUtils.copyProperties(userDetailResponse,subscriberInfo);
                subscriberInfoList.add(subscriberInfo);
            }
        }

        //subscriberInfoCustomRepository.saveSubscriberInfoList(subscriberInfoList);
        return userDetailResponseList;
    }

    @Override
    public SubscriberInfo saveSubscriberInfo(SubscriberInfo subscriberInfo){
        subscriberInfoCustomRepository.saveSubscriberInfo(subscriberInfo);
        return subscriberInfo;
    }

    @Override
    public void saveSubscriberInfoList(List<SubscriberInfo> subscriberInfoList){
        subscriberInfoCustomRepository.saveSubscriberInfoList(subscriberInfoList);
    }


    @Override
    public List<SubscriberSummary> getSubscriberSummary(String key){
        return subscriberInfoCustomRepository.getSubscriberSummary(key);
    }


}
