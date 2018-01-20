package com.demo.wechatint.wechatintegration.service;

import com.demo.wechatint.wechatintegration.dataobject.MessageResponse;
import com.demo.wechatint.wechatintegration.dataobject.MessageSummary;
import com.demo.wechatint.wechatintegration.entity.AccessToken;
import com.demo.wechatint.wechatintegration.entity.Message;
import com.demo.wechatint.wechatintegration.repository.MessageCustomRepository;
import com.demo.wechatint.wechatintegration.util.ServerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    ServerUtility serverUtility;

    @Autowired
    Environment env;

    @Autowired
    MessageCustomRepository messageCustomRepository;

    @Autowired
    AccessTokenService accessTokenService;


    @Override
    public List<Message> findAll() {
        return messageCustomRepository.getAllMessages();
    }

    private Map<String,Object> getAccessTokenMap(){
        Map<String,Object> paramMap = new HashMap<>();
        AccessToken accessToken = accessTokenService.getLatestAccessToken();
        paramMap.put("access_token",accessToken.getAccessToken());
        return paramMap;
    }
    @Override
    public MessageResponse saveAndSendMessage(Message message) {
        message = messageCustomRepository.insertMessage(message);
        MessageResponse messageResponse = serverUtility.sendTextMessage(getAccessTokenMap(),message);
        return messageResponse;
    }

    @Override
    public Message saveDefaultWelcomeMessage(Message richMessage){
        Map<String,Object> paramMap = new HashMap<>();
        richMessage = messageCustomRepository.saveDefaultWelcomeMessage(richMessage);
        return richMessage;
    }

    @Override
    public Message getDefaultMessage(){

        return messageCustomRepository.getDefaultMessage();
    }

    @Override
    public List<MessageSummary> getMessageSummaryByType(String type){
        return messageCustomRepository.getMessageSummaryByType(type);
    }
}
