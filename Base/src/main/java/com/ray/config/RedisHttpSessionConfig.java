package com.ray.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 2017/5/3 10:04
 */
@EnableRedisHttpSession
public class RedisHttpSessionConfig {

    @Bean
    public LettuceConnectionFactory connectionFactory(){
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
        connectionFactory.setHostName("myserver");
        connectionFactory.setPassword("ccY7jgeXoWDb96sV");
        connectionFactory.setPort(6379);
        return connectionFactory;
    }

}
