/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.demo.wechatint.wechatintegration.mongodb;

import com.demo.wechatint.wechatintegration.repository.SubscriberInfoCustomRepository;
import com.mongodb.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AbstractIntegrationTest.class })
public  class AbstractIntegrationTest {


	SubscriberInfoCustomRepository subscriberInfoCustomRepository;

	@Mock
	Mongo mongo;

	@Mock
	DB database;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		database = mongo.getDB("dragonconnector");

		//AccessToken
		DBCollection accessTokens = database.getCollection("accesstoken");
		accessTokens.remove(new BasicDBObject());

		BasicDBObject accessToken = new BasicDBObject();
		accessToken.put("accessToken","abcd123");
		accessToken.put("createdDate",new Date());
		accessToken.put("expiryDate", new Date());
		accessTokens.insert(accessToken);


		// MEssage

		DBCollection messages = database.getCollection("message");
		messages.drop();


		DBObject txtMsg = new BasicDBObject("touser", "vijay");
		txtMsg.put("msgtype","text");
		txtMsg.put("text",new BasicDBObject("content","HelloWorld"));
		txtMsg.put("createdDate",new Date());
		messages.insert(txtMsg);


		BasicDBList articles = new BasicDBList();
		DBObject articleItem = new BasicDBObject("title","welcome");
		articleItem.put("description","wecloem description");
		articleItem.put("url","url");
		articleItem.put("picurl","picurl");
		articles.add(articleItem);
		DBObject news = new BasicDBObject("news",articles);

		DBObject newsMsg = new BasicDBObject("touser", "vijay");
		txtMsg.put("msgtype","news");
		txtMsg.put("news",news);
		txtMsg.put("createdDate",new Date());
		messages.insert(newsMsg);

		// Orders

		DBCollection subscriberinfos = database.getCollection("subscriberinfo");
		subscriberinfos.drop();

		// Line items

		DBObject subscriber = new BasicDBObject("subscribe", 1);
		subscriber.put("openid",
		"odyRpwfSRHj00y4OFDmpVpBTHJb0");
		subscriber.put("nickname" , "Dan");
		subscriber.put(		"sex" , 1);
				subscriber.put(		"language" , "en");
				subscriber.put(		"city", "Chaoyang");
				subscriber.put(		"province" , "Beijing");
				subscriber.put(		"country" , "China");
				subscriber.put(		"headimgurl" , "http://wx.qlogo.cn/mmopen/ajNVdqHZLLByRPDjmgpCYxQ0BGywQ2h7WnyaZPJ5FuyhIA28KYaLibuLFibz9HeWZHNy9RexMkYliaB8smEWUw15g/132");
				subscriber.put(		"remark" , "");
				subscriber.put(		"groupid" , 0);
				subscriber.put(		"createTime" ,new Date());

				subscriber.put("createdDate" , new Date());
				subscriber.put("createdBy" , "SCHEDULER_JOB");


		subscriberinfos.insert(subscriber);
	}
}
