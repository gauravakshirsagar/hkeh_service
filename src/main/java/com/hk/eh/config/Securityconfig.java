package com.hk.eh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Spring boot security configuration class.
@Configuration
@EnableWebSecurity
public class Securityconfig extends WebSecurityConfigurerAdapter {

        // The Jwt token authentication filter. This filter will intercept all the requests other than the “/token” uri.
        // The class is created to fetch the authentication token from the request, parse and validate the jwt token for further processing.
      @Bean
      public Jwtauthfilter jwtAuthenticationFilter() {
         return new Jwtauthfilter();
      }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .authorizeRequests()
                    .antMatchers("/login","/register")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .csrf().disable()
                    .addFilterBefore(jwtAuthenticationFilter(),
                            UsernamePasswordAuthenticationFilter.class);

        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

