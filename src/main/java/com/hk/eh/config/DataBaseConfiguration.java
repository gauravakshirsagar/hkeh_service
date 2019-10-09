package com.hk.eh.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;


@Configuration
public class DataBaseConfiguration {

  private final Environment env;

  public DataBaseConfiguration(Environment env)
  {
      this.env=env;
  }

  @Bean
  @Primary
  public HikariDataSource getDataSource()
    {
        HikariConfig cfg= new HikariConfig();

        cfg.setJdbcUrl(env.getProperty("spring.datasource.url"));
        cfg.setUsername(env.getProperty("spring.datasource.username"));
        cfg.setPassword(env.getProperty("spring.datasource.password"));
        cfg.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        cfg.setMaximumPoolSize(5);

        return  new HikariDataSource(cfg);
    }
}
