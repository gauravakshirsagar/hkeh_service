package com.hk.eh.service;

import com.hk.eh.dto.MailContentDTO;
import com.hk.eh.dto.MailPersonalizationDTO;
import com.hk.eh.dto.MailUserDTO;
import com.hk.eh.dto.SendGridMailTemplateDTO;
import com.hk.eh.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Slf4j
@Service
@Transactional
public class MailService {
    @Autowired
    RestTemplate restTemplate;

    public Object sendMail(SendGridMailTemplateDTO request) throws URISyntaxException {

        Map<String , String> headerMap = new HashMap<>();
        headerMap.put("Authorization", "Bearer ");
        String url ="https://api.sendgrid.com/v3/mail/send";
        return sendRequest(url, request, new ParameterizedTypeReference<String>() {},HttpMethod.POST, null,headerMap);
    }

    private Object sendRequest(String path , Object request, ParameterizedTypeReference<String> typeReference, HttpMethod method, Map<String,String> uriParams,Map<String,String> headerMap) throws URISyntaxException {
        log.debug(" sendRequest >> START");
        log.debug(" sendRequest >> request "+request.toString());
        URI uri = new URI(path);
        ResponseEntity<String> restResponse= null;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri.toString());
        HttpEntity<Object> httpEntity = new HttpEntity<>(request, prepareHttpHeaders(headerMap));
        log.debug(" sendRequest >> httpEntity "+httpEntity.toString());

        if(null != uriParams)
        {
            for(Map.Entry<String,String> entity:uriParams.entrySet())
            {
                builder.queryParam(entity.getKey(),entity.getValue());
            }
        }
        log.debug("url to be called :: "+builder.toUriString());
        restResponse = restTemplate.exchange(builder.toUriString(),method,httpEntity,typeReference);
        log.debug("response status code : "+restResponse.getStatusCode()+" restreponse body :: "+restResponse.getBody().toString());
        return restResponse;
    }

    private HttpHeaders prepareHttpHeaders(Map<String, String> headerMap) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        if(null != headerMap)
        {
            for(Map.Entry<String,String> entity:headerMap.entrySet())
            {
                requestHeaders.set(entity.getKey(),entity.getValue());
            }
        }
        return  requestHeaders;
    }


}
