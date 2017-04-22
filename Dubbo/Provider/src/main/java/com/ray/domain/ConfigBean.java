package com.ray.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration                                  //如果自定义配置文件位置，那么要加上这句话
@ConfigurationProperties(prefix = "com.ray")    //只要指定前缀，能够自动注入
@PropertySource("classpath:my.properties")      //自定义配置文件位置
public class ConfigBean {

    private String name;

    private String want;

    private String random;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWant() {
        return want;
    }

    public void setWant(String want) {
        this.want = want;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }
}
