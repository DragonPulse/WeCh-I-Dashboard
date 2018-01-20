package com.demo.wechatint.wechatintegration.repository;

import com.demo.wechatint.wechatintegration.entity.AccessToken;

public interface AccessTokenCustomRepository {

    public AccessToken insertAccessToken(AccessToken accessToken);

    public AccessToken getLatestAccessToken();
}
