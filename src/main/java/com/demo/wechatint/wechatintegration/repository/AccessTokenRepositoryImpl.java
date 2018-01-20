package com.demo.wechatint.wechatintegration.repository;

import com.demo.wechatint.wechatintegration.entity.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccessTokenRepositoryImpl implements AccessTokenCustomRepository {

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public AccessToken insertAccessToken(AccessToken accessToken) {
        accessToken = accessTokenRepository.save(accessToken);
        return accessToken;
    }

    @Override
    public AccessToken getLatestAccessToken() {
        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query();
        query.with(new Sort(Sort.Direction.DESC, "expiryDate"));
        return mongoTemplate.findOne(query,AccessToken.class);
    }

    public AccessTokenRepository getAccessTokenRepository() {
        return accessTokenRepository;
    }

    public void setAccessTokenRepository(AccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
    }
}
