package com.demo.wechatint.wechatintegration.util;

import com.demo.wechatint.wechatintegration.config.LogExecutionTime;
import com.demo.wechatint.wechatintegration.dataobject.AccessTokenResponse;
import com.demo.wechatint.wechatintegration.dataobject.MessageResponse;
import com.demo.wechatint.wechatintegration.dataobject.UserDetailResponse;
import com.demo.wechatint.wechatintegration.dataobject.UserResponse;
import com.demo.wechatint.wechatintegration.entity.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Component
public class ServerUtility {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    public Environment env;

    public static void main(String args[]){
       /* ServerUtility serverUtility = new ServerUtility();
        serverUtility.setRestTemplate(new RestTemplate());
        Map<String,Object> paramMap = new HashMap<>();
        String access_token="5_HzBCBghYDmzeYpY2WGDerBE0QLIbsMpBJbyiK-Dij2HSDVBVUq8Tz0HVr9ZSiWxps8Ogse7Nrys2e7OO2TYDH9V6f8c7YW1LUEC6ukxdC4226vjE-vfpw4h4z48JNYcAEAUYJ";
        paramMap.put("access_token",access_token);
        System.out.println(serverUtility.getUserOpenIds(paramMap));*/


        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(1516298232l * 1000);
        System.out.println(cal.getTime());


    }

    @LogExecutionTime
    public <T> T callServerWithPostMethod(final String url, final Class<T> returnTypeClass,
                                          final List<MediaType> mediaTypes, final Map<String, String> headerParams,
                                          final Object bodyObject,final Map<String, Object> queryParams) throws Exception {

        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(mediaTypes);
        setHeaderParamsIfExists(headers, headerParams);
        final HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        setQueryParamsIfExists(uriBuilder, queryParams);


        HttpEntity entity = new HttpEntity(bodyObject, headers);

        final ResponseEntity<T> responseEntity=restTemplate.exchange(getUrl(uriBuilder), HttpMethod.POST, entity, returnTypeClass);

        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        }else {
            return null;
        }

    }



    @LogExecutionTime
    public MessageResponse sendTextMessage(Map<String,Object> paramsMap,Message message){
        MessageResponse messageResponse = null;
        try{
            messageResponse = callServerWithPostMethod(env.getProperty("wechat.sendmessage"),
                    MessageResponse.class,
                    Collections.singletonList(MediaType.APPLICATION_JSON),
                    null,
                    message,
                    paramsMap);
        }catch(Exception e){
            e.printStackTrace();
        }
        return messageResponse;
    }

    @LogExecutionTime
    public AccessTokenResponse generateAccessToken(Map<String,Object> paramsMap){
        AccessTokenResponse accessTokenResponse = null;
        try{
            accessTokenResponse = callServerWithGetMethod(env.getProperty("wechat.generateaccesstoken"),AccessTokenResponse.class,
                    Collections.singletonList(MediaType.APPLICATION_JSON),null,paramsMap);
        }catch(Exception e){
            e.printStackTrace();
        }
        return accessTokenResponse;
    }

    @LogExecutionTime
    public UserResponse getUserOpenIds(Map<String,Object> paramsMap){
        UserResponse userResponse = null;
        try{
            userResponse = callServerWithGetMethod(env.getProperty("wechat.getusers"),UserResponse.class,
                    Collections.singletonList(MediaType.APPLICATION_JSON),null,paramsMap);
        }catch(Exception e){
            e.printStackTrace();
        }
        return userResponse;
    }

    @LogExecutionTime
    public UserDetailResponse getUserInfoByOpenId(Map<String,Object> paramsMap){
        UserDetailResponse userDetailResponse = null;
        try{
            userDetailResponse = callServerWithGetMethod(env.getProperty("wechat.getuserinfo"),UserDetailResponse.class,
                    Collections.singletonList(MediaType.APPLICATION_JSON),null,paramsMap);
        }catch(Exception e){
            e.printStackTrace();
        }
        return userDetailResponse;
    }





    @LogExecutionTime
    public <T> T callServerWithGetMethod(final String url, final Class<T> returnTypeClass,
                                         final List<MediaType> mediaTypes, final Map<String, String> headerParams,
                                         final Map<String, Object> queryParams) throws Exception {

        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(mediaTypes);
        setHeaderParamsIfExists(headers, headerParams);
        final HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        setQueryParamsIfExists(uriBuilder, queryParams);

        final ResponseEntity<T> entity = restTemplate.exchange(getUrl(uriBuilder), HttpMethod.GET, requestEntity,
                returnTypeClass);
        if(entity.getStatusCode() == HttpStatus.OK) {
            return entity.getBody();
        }else {
            return null;
        }

    }

    private void setHeaderParamsIfExists(HttpHeaders headers, Map<String, String> headerParams) {
        if(headerParams != null && !headerParams.isEmpty()){
            for(Map.Entry<String,String> entry:headerParams.entrySet()){
                headers.set(entry.getKey(),entry.getValue());
            }
        }


    }

    private void setQueryParamsIfExists(UriComponentsBuilder uriBuilder, Map<String, Object> queryParams) {
        if(queryParams != null && !queryParams.isEmpty()){
            for( Map.Entry<String,Object> entry: queryParams.entrySet()){
                uriBuilder.queryParam(entry.getKey(), entry.getValue());
            }
        }
    }

    private URI getUrl(UriComponentsBuilder uriBuilder) {
        return uriBuilder.build().encode().toUri();
    }


    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
