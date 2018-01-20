package com.demo.wechatint.wechatintegration.service;

import com.demo.wechatint.wechatintegration.constants.AppConstants;
import com.demo.wechatint.wechatintegration.dataobject.AccessTokenResponse;
import com.demo.wechatint.wechatintegration.entity.AccessToken;
import com.demo.wechatint.wechatintegration.repository.AccessTokenCustomRepository;
import com.demo.wechatint.wechatintegration.util.DateUtil;
import com.demo.wechatint.wechatintegration.util.ServerUtility;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Autowired
    ServerUtility serverUtility;

    @Autowired
    Environment env;

    @Autowired
    AccessTokenCustomRepository accessTokenRepository;

    @Autowired
    DateUtil dateUtil;

    @Override
    public AccessTokenResponse generateAccessToken() {
        AccessTokenResponse accessTokenResponse = null;
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put(AppConstants.PARAM_KEY_GRANT_TYPE,AppConstants.PARAM_VALUE_CLIENT_CREDENTIAL);
        paramsMap.put(AppConstants.PARAM_KEY_APP_ID,env.getProperty("wechat.appid"));
        paramsMap.put(AppConstants.PARAM_KEY_SECRET,env.getProperty("wechat.secret"));
        accessTokenResponse= serverUtility.generateAccessToken(paramsMap);
        return accessTokenResponse;
    }

    @Override
    public AccessToken insertAccessToken(AccessTokenResponse accessTokenResponse) {
        AccessToken accessToken = new AccessToken();
        accessToken.setCreatedDate(dateUtil.getCurrentDate());
        accessToken.setExpiryDate(dateUtil.addSecondsToDate(accessToken.getCreatedDate(),accessTokenResponse.getExpiresIn()));
        accessToken.setAccessToken(accessTokenResponse.getAccessToken());
        accessToken =accessTokenRepository.insertAccessToken(accessToken);
        return accessToken;
    }

    @Override
    public AccessToken getLatestAccessToken() {
        return accessTokenRepository.getLatestAccessToken();
    }
}
