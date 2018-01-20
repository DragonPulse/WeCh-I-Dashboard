package com.demo.wechatint.wechatintegration.repository;


import com.demo.wechatint.wechatintegration.dataobject.MessageSummary;
import com.demo.wechatint.wechatintegration.dataobject.SubscriberSummary;
import com.demo.wechatint.wechatintegration.entity.Message;
import com.demo.wechatint.wechatintegration.entity.SubscriberInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class MessageRepositoryImpl implements MessageCustomRepository {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Message insertMessage(Message message){
        message = messageRepository.save(message);
        return message;
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message saveDefaultWelcomeMessage(Message richMessage){
        return messageRepository.save(richMessage);
    }

    @Override
    public Message getDefaultMessage(){
       List<Message> messageList =  messageRepository.findByTouser("Default");
       return messageList.get(0);
    }

    @Override
    public List<MessageSummary> getMessageSummaryByType(String type){

        Aggregation agg = newAggregation(
                project()
                        .andExpression("year(createdDate)").as("year")
                        .andExpression("month(createdDate)").as("month")
                        .andExpression("dayOfMonth(createdDate)").as("day"),
                group(fields().and("year").and("month").and("day"))
                        .count().as("count")

        );

        AggregationResults<MessageSummary> results = mongoTemplate.aggregate(agg, Message.class, MessageSummary.class);
        List<MessageSummary> messageSummaryCount = results.getMappedResults();
        return messageSummaryCount;
    }

    public MessageRepository getMessageRepository() {
        return messageRepository;
    }

    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
}
