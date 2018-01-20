package com.demo.wechatint.wechatintegration.controller;

import com.demo.wechatint.wechatintegration.config.LogExecutionTime;
import com.demo.wechatint.wechatintegration.constants.AppConstants;
import com.demo.wechatint.wechatintegration.dataobject.UserDetailResponse;
import com.demo.wechatint.wechatintegration.dataobject.Xml;
import com.demo.wechatint.wechatintegration.entity.Message;
import com.demo.wechatint.wechatintegration.entity.SubscriberInfo;
import com.demo.wechatint.wechatintegration.service.MessageService;
import com.demo.wechatint.wechatintegration.service.UserService;
import com.demo.wechatint.wechatintegration.util.DateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.logging.Logger;

@Controller
public class ViewController {

    static private Logger LOGGER = Logger.getLogger(ViewController.class.getName());

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @Autowired
    DateUtil dateUtil;


    @LogExecutionTime
    private Xml generateXml(String wechatPushedMesssage) throws Exception{
        ObjectMapper objectMapper = new XmlMapper();
        Xml xml = objectMapper.readValue(StringUtils.toEncodedString(wechatPushedMesssage.getBytes(), StandardCharsets.UTF_8),Xml.class);
        return xml;
    }

    @LogExecutionTime
    private String getMessageXMLString(InputStream messageStream) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(messageStream));
        StringBuffer xmlStringBuffer = new StringBuffer();
        String readLine;
        while ((readLine = br.readLine()) != null) {
            xmlStringBuffer.append(readLine);
        }
        LOGGER.info("xmlStringBuffer = " + xmlStringBuffer);
        return xmlStringBuffer.toString();
    }

    @LogExecutionTime
    private void checkEventAndTakeAction(Xml  xml)throws Exception{
        if(xml.getEvent().equals(AppConstants.EVENT_TYPE_SUBSCRIBE)){
            createSubAndSendWelcomeMsg(xml);
        }
    }

    @LogExecutionTime
    private void  createSubAndSendWelcomeMsg(Xml xml) throws Exception{
        SubscriberInfo subscriberInfo = new SubscriberInfo();
        UserDetailResponse userDetailResponse = userService.getUserInfo(xml.getFromUserName());
        BeanUtils.copyProperties(userDetailResponse, subscriberInfo);
        subscriberInfo.setCreateTime(dateUtil.getDateFromLong(Long.parseLong(xml.getCreateTime())));
        userService.saveSubscriberInfo(subscriberInfo);
        Message defaultMessage = messageService.getDefaultMessage();
        Message message = new Message();
        BeanUtils.copyProperties(defaultMessage,message,"id");
        message.setTouser(xml.getFromUserName());
        messageService.saveAndSendMessage(message);
    }


    @LogExecutionTime
    @RequestMapping(value = "/",method = RequestMethod.POST, produces = "application/json" )
    public @ResponseBody String processWeChatMessage(HttpServletRequest request) throws Exception{
        String echostr = request.getParameter(AppConstants.WE_CHAT_REQUEST_PARAM_ECHOSTR);
        if (echostr != null) {
            LOGGER.info("Test server - app server verfication request triggered. Returning echostr");
            return echostr;
        }
        InputStream requestBody = request.getInputStream();
        String messageXMLString = getMessageXMLString(requestBody);
        Xml xmlObjectFromWeChat = generateXml(messageXMLString);
       // UserAndEvent userAndEvent = getUserAndEvent(messageXMLString);
        //LOGGER.info("event = " + userAndEvent.getEventType() + ": " + userAndEvent.getOpenId());
        checkEventAndTakeAction(xmlObjectFromWeChat);
        return "success";
    }

    @LogExecutionTime
    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @LogExecutionTime
    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String home(){
        return "index";
    }

    @LogExecutionTime
    @RequestMapping(value="/sendMessage",method = RequestMethod.GET)
    public String sendmessage(){
        return "sendmessage";
    }

    @LogExecutionTime
    @RequestMapping(value="/allOpenIds",method = RequestMethod.GET)
    public String getAllOpenIds() {
        return "allopenids";
    }

    @LogExecutionTime
    @RequestMapping(value="/allUserDetails",method = RequestMethod.GET)
    public String getAllUserDetails(){
        return "alluserdetails";
    }

    @LogExecutionTime
    @RequestMapping(value="/allMessages",method = RequestMethod.GET)
    public String allMessages(){
        return "allmessages";
    }

    @LogExecutionTime
    @RequestMapping(value="/richMessage",method = RequestMethod.GET)
    public String welcomeMessage(HttpServletRequest request, HttpServletResponse response){
        return "welcomemessage";
    }

    @LogExecutionTime
    @RequestMapping(value="/welcomeMessage",method = RequestMethod.GET)
    public String richMessage(){ return "welcomemessage"; }

}




