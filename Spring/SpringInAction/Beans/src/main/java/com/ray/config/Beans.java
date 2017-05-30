package com.ray.config;

import com.ray.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ray on 2017/5/30.
 */
@Configuration
public class Beans {

    @Bean
    public User user(){
        return new User();
    }

}
