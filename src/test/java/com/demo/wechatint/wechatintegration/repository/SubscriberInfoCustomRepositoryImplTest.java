package com.demo.wechatint.wechatintegration.repository;

import com.demo.wechatint.wechatintegration.entity.SubscriberInfo;
import com.demo.wechatint.wechatintegration.mongodb.AbstractIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SubscriberInfoCustomRepositoryImplTest extends AbstractIntegrationTest{


    SubscriberInfoCustomRepository subscriberInfoCustomRepository;

    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
        subscriberInfoCustomRepository = new SubscriberInfoCustomRepositoryImpl();
    }

    @Test
    public void saveSubscriberInfo() {

        SubscriberInfo subscriberInfo = new SubscriberInfo();
        subscriberInfo.setCity("Hyderbad");
        subscriberInfo.setLanguage("en");
        subscriberInfo= subscriberInfoCustomRepository.saveSubscriberInfo(subscriberInfo);
        assertThat(subscriberInfo.getId(), is(notNullValue()));
    }
}
