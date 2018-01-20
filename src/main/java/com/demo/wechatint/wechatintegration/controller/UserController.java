package com.demo.wechatint.wechatintegration.controller;

import com.demo.wechatint.wechatintegration.config.LogExecutionTime;
import com.demo.wechatint.wechatintegration.dataobject.SubscriberSummary;
import com.demo.wechatint.wechatintegration.dataobject.UserDetailResponse;
import com.demo.wechatint.wechatintegration.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;



    @LogExecutionTime
    @RequestMapping("/allOpenIdsNew")
    public @ResponseBody List<UserDetailResponse> getAllOpenIdsNew(Model model) throws Exception{
        List<UserDetailResponse> userDetailResponseList = new ArrayList<>();
        for(String openId: userService.getAllOpenIds()){
            UserDetailResponse userDetailResponse = new UserDetailResponse();
            userDetailResponse.setOpenid(openId);
            userDetailResponseList.add(userDetailResponse);
        }
        return userDetailResponseList;
    }


    @LogExecutionTime
    @RequestMapping(value = "/getSubscriberSummary",produces = "application/json")
    public @ResponseBody List<SubscriberSummary> getSubscriberInfoList(@RequestParam("infoType") String infoType){
        List<SubscriberSummary> subscriberSummaryList = userService.getSubscriberSummary(infoType);
        return subscriberSummaryList;
    }

    @LogExecutionTime
    @RequestMapping("/allUserDetailsNew")
    public @ResponseBody  List<UserDetailResponse> allUserDetailsNew(Model model) throws Exception{
        List<UserDetailResponse> userDetailResponseList =  userService.getAllUserInfo();
        return userDetailResponseList;
    }
}
