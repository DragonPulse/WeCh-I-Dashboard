package com.demo.wechatint.wechatintegration.repository;


import com.demo.wechatint.wechatintegration.dataobject.SubscriberSummary;
import com.demo.wechatint.wechatintegration.entity.SubscriberInfo;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SubscriberInfoCustomRepository {

    public SubscriberInfo insertSubscriberInfo(SubscriberInfo subscriberInfo);

    public List<SubscriberInfo> getAllSubscriberInfo();

    public void saveSubscriberInfoList(List<SubscriberInfo> subscriberInfoList);

    public List<SubscriberInfo> findAllOpenIds();

    public SubscriberInfo saveSubscriberInfo(SubscriberInfo subscriberInfo);

    public List<SubscriberSummary> getSubscriberSummary(String key);
}
