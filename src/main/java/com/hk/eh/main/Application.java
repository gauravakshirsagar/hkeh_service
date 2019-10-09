package com.hk.eh.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@ComponentScan("com.hk")
@EntityScan("com.hk")
@EnableTransactionManagement
@EnableJpaAuditing
@EnableJpaRepositories("com.hk")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
