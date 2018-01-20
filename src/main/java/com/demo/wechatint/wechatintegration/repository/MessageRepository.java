package com.demo.wechatint.wechatintegration.repository;

import com.demo.wechatint.wechatintegration.dataobject.MessageSummary;
import com.demo.wechatint.wechatintegration.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message,String> {


    void delete(Message message);

    List<Message> findAll();

    Message findOne(String messageId);

    Message save(Message message);

    List<Message> findByTouser(String toUser);



}
