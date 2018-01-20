package com.demo.wechatint.wechatintegration.repository;

import com.demo.wechatint.wechatintegration.entity.AccessToken;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AccessTokenRepository extends MongoRepository<AccessToken,String> {


    void delete(AccessToken accessTokenToDelete);

    List<AccessToken> findAll();

    AccessToken findOne(String accessToken);

    AccessToken save(AccessToken accessToken);

}
