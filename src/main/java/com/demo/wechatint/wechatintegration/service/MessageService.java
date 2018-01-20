package com.demo.wechatint.wechatintegration.service;

import com.demo.wechatint.wechatintegration.dataobject.MessageResponse;
import com.demo.wechatint.wechatintegration.dataobject.MessageSummary;
import com.demo.wechatint.wechatintegration.entity.Message;

import java.util.List;

public interface MessageService {

    List<Message> findAll();
    MessageResponse saveAndSendMessage(Message message);
    Message saveDefaultWelcomeMessage(Message richMessage);
    Message getDefaultMessage();
    List<MessageSummary> getMessageSummaryByType(String type);

}
