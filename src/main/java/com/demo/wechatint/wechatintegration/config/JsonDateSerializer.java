package com.demo.wechatint.wechatintegration.config;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;

import org.codehaus.jackson.JsonProcessingException;

import org.codehaus.jackson.map.JsonSerializer;

import org.codehaus.jackson.map.SerializerProvider;

import org.springframework.stereotype.Component;



@Component
public class JsonDateSerializer extends JsonSerializer<Date>{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(1516298232l * 1000);
        System.out.println(cal.getTime());


        gen.writeString(cal.getTime().toString());
    }

}