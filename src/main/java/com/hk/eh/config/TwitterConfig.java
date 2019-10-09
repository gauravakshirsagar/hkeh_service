package com.hk.eh.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
@Slf4j
public class TwitterConfig {

    private final Environment env;
    public TwitterConfig(Environment env)
    {
        this.env=env;
    }

    @Bean
    public Twitter getTwitterTemplate() {
        log.debug("getTwitterTemplate >> START");
        String consumerKey = env.getProperty("social.twitter.consumerKey");
        String consumerSecret = env.getProperty("social.twitter.consumerSecret");
        String accessToken = env.getProperty("social.twitter.accessToken");
        String accessTokenSecret = env.getProperty("social.twitter.accessTokenSecret");

        log.debug("consumerKey >> "+consumerKey);
        log.debug("consumerSecret"+consumerSecret);
        log.debug("accessToken"+accessToken);
        log.debug("accessTokenSecret"+accessTokenSecret);
        TwitterTemplate twitterTemplate =
                new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        return twitterTemplate;
    }
}
