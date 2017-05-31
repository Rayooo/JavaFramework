package com.ray.config;

import com.ray.domain.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by Ray on 2017/5/30.
 */
@Configuration
public class Beans {

    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)   //会为每个注入的实例创建一个bean
    public User user(){
        return new User();
    }

}
