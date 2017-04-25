package com.ray;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Create by Ray on 2017/4/25 16:29
 */
@EnableRedisHttpSession
public class Config {

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
        connectionFactory.setHostName("myserver");
        connectionFactory.setPassword("defaultPassword");
        connectionFactory.setPort(6379);
        return connectionFactory;
    }



}
