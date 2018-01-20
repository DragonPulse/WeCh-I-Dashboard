package com.demo.wechatint.wechatintegration.repository;


import com.demo.wechatint.wechatintegration.entity.SubscriberInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubscriberInfoRepository extends MongoRepository<SubscriberInfo,String> {


    void delete(SubscriberInfo subscriberInfo);

    List<SubscriberInfo> findAll();

    SubscriberInfo findOne(String subscriberInfo);

    SubscriberInfo save(SubscriberInfo subscriberInfo);

}
