package com.demo.wechatint.wechatintegration.repository;


import com.demo.wechatint.wechatintegration.dataobject.MessageSummary;
import com.demo.wechatint.wechatintegration.entity.Message;

import java.util.List;

public interface MessageCustomRepository {

    Message insertMessage(Message message);

    List<Message> getAllMessages();

    Message saveDefaultWelcomeMessage(Message richMessage);

    Message getDefaultMessage();

    List<MessageSummary> getMessageSummaryByType(String type);

}
