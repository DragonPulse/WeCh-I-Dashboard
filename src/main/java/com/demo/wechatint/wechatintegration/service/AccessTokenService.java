package com.demo.wechatint.wechatintegration.service;

import com.demo.wechatint.wechatintegration.dataobject.AccessTokenResponse;
import com.demo.wechatint.wechatintegration.entity.AccessToken;

public interface AccessTokenService {

    public AccessTokenResponse generateAccessToken();

    public AccessToken insertAccessToken(AccessTokenResponse accessTokenResponse);

    public AccessToken getLatestAccessToken();
}
