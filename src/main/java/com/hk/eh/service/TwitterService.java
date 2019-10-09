package com.hk.eh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class TwitterService {

    @Autowired
    Twitter twitter;

    public Object getPost(String searchString) throws Exception{
        log.debug("getPost searchString >>> "+searchString);
       return twitter.searchOperations().search(searchString);//"#ARRAYforest"
    }
}
