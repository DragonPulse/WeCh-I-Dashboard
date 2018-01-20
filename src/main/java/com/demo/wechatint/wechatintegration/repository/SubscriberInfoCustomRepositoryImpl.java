package com.demo.wechatint.wechatintegration.repository;

import com.demo.wechatint.wechatintegration.dataobject.SubscriberSummary;
import com.demo.wechatint.wechatintegration.entity.AccessToken;
import com.demo.wechatint.wechatintegration.entity.SubscriberInfo;
import com.mongodb.client.model.Projections;
import org.springframework.beans.factory.annotation.Autowired;

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
public class SubscriberInfoCustomRepositoryImpl implements SubscriberInfoCustomRepository {

    @Autowired
    SubscriberInfoRepository subscriberInfoRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public SubscriberInfo insertSubscriberInfo(SubscriberInfo subscriberInfo) {
        subscriberInfo = subscriberInfoRepository.save(subscriberInfo);
        return subscriberInfo;
    }

    @Override
    public void saveSubscriberInfoList(List<SubscriberInfo> subscriberInfoList) {
        subscriberInfoRepository.save(subscriberInfoList);
    }


    @Override
    public List<SubscriberInfo> getAllSubscriberInfo() {
        return subscriberInfoRepository.findAll();
    }

    @Override
    public List<SubscriberInfo> findAllOpenIds(){
        Query query = new Query();
        query.fields().include("openid").exclude("id");
        List<SubscriberInfo> subscriberInfoList = mongoTemplate.find(query, SubscriberInfo.class);
        return subscriberInfoList;
    }


    @Override
    public SubscriberInfo saveSubscriberInfo(SubscriberInfo subscriberInfo){
        if(subscriberInfo.getOpenid()!=null){
            subscriberInfoRepository.save(subscriberInfo);
            return subscriberInfo;
        }
        return subscriberInfo;

    }

    @Override
    public List<SubscriberSummary> getSubscriberSummary(String key){
        Aggregation agg = newAggregation(
                project(key),
                group(key).count().as("count"),
                project("count").and(key).previousOperation(),
                sort(DESC, "count")
        );
        AggregationResults<SubscriberSummary> results = mongoTemplate.aggregate(agg, SubscriberInfo.class, SubscriberSummary.class);
        List<SubscriberSummary> subscriberSummaryCount = results.getMappedResults();
        return subscriberSummaryCount;
    }




    public SubscriberInfoRepository getSubscriberInfoRepository() {
        return subscriberInfoRepository;
    }

    public void setSubscriberInfoRepository(SubscriberInfoRepository subscriberInfoRepository) {
        this.subscriberInfoRepository = subscriberInfoRepository;
    }
}
