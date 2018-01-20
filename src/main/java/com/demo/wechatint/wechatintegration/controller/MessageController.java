package com.demo.wechatint.wechatintegration.controller;

import com.demo.wechatint.wechatintegration.config.LogExecutionTime;
import com.demo.wechatint.wechatintegration.constants.AppConstants;
import com.demo.wechatint.wechatintegration.dataobject.GenericResponseJson;
import com.demo.wechatint.wechatintegration.dataobject.MessageResponse;
import com.demo.wechatint.wechatintegration.dataobject.MessageSummary;
import com.demo.wechatint.wechatintegration.entity.*;
import com.demo.wechatint.wechatintegration.service.MessageService;
import com.demo.wechatint.wechatintegration.service.UserService;
import com.demo.wechatint.wechatintegration.util.DateUtil;
import com.demo.wechatint.wechatintegration.util.ServerUtility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;


    @Autowired
    ServerUtility serverUtility;

    @Autowired
    UserService userService;

    @Autowired
    DateUtil dateUtil;

    @LogExecutionTime
    @RequestMapping(value="/sendMessage",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public GenericResponseJson sendMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MessageResponse messageResponse = null;
        Map<String,String[]> requestMap = request.getParameterMap();
        Boolean sentFlag= false;
        sentFlag = sendMessage(requestMap);


        if(sentFlag){
            return new GenericResponseJson(AppConstants.RESPONSE_TYPE_SUCCESS,"10","Message Sent Successfully to Subscriber");
        }else{
            return new GenericResponseJson(AppConstants.RESPONSE_TYPE_ERROR,"20","Seems like Some Error, not able to send message to  Subscriber");

        }

    }




    @LogExecutionTime
    @RequestMapping(value = "/saveWelcomeMessage",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public GenericResponseJson saveWelcomeMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,String[]> requestMap = request.getParameterMap();
        List<Message> messagesList = generateRichMessage(requestMap,AppConstants.MESSGAE_TYPE_NEWS);
        for(Message message : messagesList){
            message = messageService.saveDefaultWelcomeMessage(message);
        }
        return new GenericResponseJson(AppConstants.RESPONSE_TYPE_SUCCESS,"10","Message Saved Successfully as Default Message");
    }


    @RequestMapping(value = "/getMessageSummary",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    public List<MessageSummary> getMessageSummary(@RequestParam("infoType") String infoType){
        List<MessageSummary> summaries = messageService.getMessageSummaryByType("createdDate");
        return summaries;
    }


    @LogExecutionTime
    @RequestMapping(value="/getAllMessages",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    public List<Message> getAllMessages(HttpServletRequest request, HttpServletResponse response){
        Map<String,String> subNameOpenIdMap = userService.getSubNameOpenIdMap();
        List<Message> messagesList = messageService.findAll();
        for (final ListIterator<Message> i = messagesList.listIterator(); i.hasNext();) {
            final Message element = (Message)i.next();
            element.setNickName(subNameOpenIdMap.get(element.getTouser()));
            i.set(element);
        }
        return messagesList;
    }



    @LogExecutionTime
    private List<Message> generateMessageObj(Map<String,String[]> requestMap) throws IOException {
        if(requestMap.get("messageType")[0] .equals(AppConstants.REQUEST_PARAM_MESSAGE_TYPE_RICH)){
            return generateRichMessage(requestMap,AppConstants.MESSGAE_TYPE_NEWS);
        }else if(requestMap.get("messageType")[0] .equals(AppConstants.REQUEST_PARAM_MESSAGE_TYPE_TEXT)){
            return generateTextMessage(requestMap,AppConstants.MESSAGE_TYPE_TEXT);
        }else{
            return new ArrayList<>();
        }
    }

    @LogExecutionTime
    private  List<Message> generateTextMessage(Map<String,String[]> requestMap,String msgType){
        List<Message> messagesList = new ArrayList<>();
        List<String> openIds = new ArrayList<String>(Arrays.asList(requestMap.get("openId")));
        for (String openId: openIds){
            Message message = new Message();
            message.setMsgtype(msgType);
            message.setTouser(openId);
            message.setText(new Text(requestMap.get("txtMessage")[0].toString()));
            message.setCreatedDate(new Date());
            messagesList.add(message);
        }

        return messagesList;
    }

    @LogExecutionTime
    private List<Message> generateRichMessage(Map<String,String[]> requestMap,String msgType) throws IOException  {
        List<Message> messagesList = new ArrayList<>();
        List<String> openIds;
        if(!requestMap.containsKey("openId")){
            openIds = Arrays.asList("Default");
        }else{
            openIds = new ArrayList<String>(Arrays.asList(requestMap.get("openId")));
        }
        for (String openId: openIds){
            Message richMessage;
            News news = new News();
            richMessage = new Message();
            richMessage.setTouser(openId);
            String[] valueArray = requestMap.get("articles");
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Article> articleObjsFromRequest = objectMapper.readValue(valueArray[0] , new TypeReference<List<Article>>(){});

            news.setArticles(articleObjsFromRequest);
            richMessage.setNews(news);
            richMessage.setCreatedDate(dateUtil.getCurrentDate());
            richMessage.setMsgtype(msgType);
            messagesList.add(richMessage);
        }
        return messagesList;
    }

    @LogExecutionTime
    private Boolean sendMessage(Map<String,String[]> requestMap) throws IOException{
        List<Message> messagesList = generateMessageObj(requestMap);
        for(Message message : messagesList){
            MessageResponse messageResponse = messageService.saveAndSendMessage(message);
        }
        return true;
    }

}
