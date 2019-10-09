package com.hk.eh.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class RestConfig {

    private final Environment env;
    public RestConfig(Environment env)
    {
        this.env=env;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        log.debug("RestTemplate config >> START");
     return new RestTemplate();
    }
}
