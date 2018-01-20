package com.demo.wechatint.wechatintegration.util;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class DateUtil {

    public  Date getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public  Date addSecondsToDate(Date date,int numberOfSeconds){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, numberOfSeconds);
        return calendar.getTime();
    }

    public  Date getDateFromLong(Long timeMiliseconds){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeMiliseconds * 1000);
        return cal.getTime();
    }
}
