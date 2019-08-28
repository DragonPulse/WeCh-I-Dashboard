package com.demo.wechatint.wechatintegration.util;

import com.demo.wechatint.wechatintegration.config.LogExecutionTime;
import com.demo.wechatint.wechatintegration.dataobject.AccessTokenResponse;
import com.demo.wechatint.wechatintegration.dataobject.UserDetailResponse;
import com.demo.wechatint.wechatintegration.dataobject.UserResponse;
import com.demo.wechatint.wechatintegration.entity.AccessToken;
import com.demo.wechatint.wechatintegration.entity.Article;
import com.demo.wechatint.wechatintegration.entity.SubscriberInfo;
import com.demo.wechatint.wechatintegration.repository.AccessTokenCustomRepository;
import com.demo.wechatint.wechatintegration.repository.AccessTokenRepository;
import com.demo.wechatint.wechatintegration.service.AccessTokenService;
import com.demo.wechatint.wechatintegration.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;


@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");



    @Autowired
    AccessTokenService accessTokenService;

    @Autowired
    UserService userService;

    @Autowired
    DateUtil dateUtil;




    /**
     * Executed in first one second later it will be executed every 1.6 hours
     */
    @LogExecutionTime
    @Scheduled(fixedRate = 6000000,initialDelay = 1000) //6000000 miliseconds
    public void accessTokenGeneratorScheduler() {
        AccessTokenResponse accessTokenResponse = accessTokenService.generateAccessToken();
        accessTokenService.insertAccessToken(accessTokenResponse);
        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
    }


    @LogExecutionTime
    @Scheduled(fixedRate = 6000000,initialDelay = 2500) //6000000 miliseconds
    public void subscriberInfoLoaderScheduler() throws Exception {
        UserResponse userResponse = userService.getUserOpenIds();
        List<SubscriberInfo> subscriberInfoList = new ArrayList<>();
        for(String openId: userResponse.getData().getOpenid()){
            UserDetailResponse userDetailResponse =  userService.getUserInfo(openId);
            SubscriberInfo subscriberInfo = new SubscriberInfo();
            BeanUtils.copyProperties(userDetailResponse,subscriberInfo);
            subscriberInfo.setCreatedDate(dateUtil.getCurrentDate());
            subscriberInfo.setCreatedBy("SCHEDULER_JOB");
            subscriberInfoList.add(subscriberInfo);
            logger.info(""+subscriberInfo);
        }
        userService.saveSubscriberInfoList(subscriberInfoList);
        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
    }

    /**
     * Executed in first one second later it will be executed every 1.6 hours
     */
    //@Scheduled(fixedRate = 5000) //6000000 miliseconds
    public void getAccessToken() {
        AccessToken accessToken = accessTokenService.getLatestAccessToken();
        logger.info("Fixed Rate Task :: Execution Time - {} +"+accessToken.getAccessToken()+ "::", dateTimeFormatter.format(LocalDateTime.now()));
    }

}
