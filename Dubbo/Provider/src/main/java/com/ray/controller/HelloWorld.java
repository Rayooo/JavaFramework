package com.ray.controller;

import com.ray.domain.ConfigBean;
import com.ray.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    //@Value("${com.ray.name}")   //可以通过这种方式绑定自定义属性到变量
    //private String name;

    //@Value("${com.ray.want}")   //可以通过这种方式绑定自定义属性到变量
    //private String want;

    @Autowired
    private ConfigBean configBean;

    @Autowired
    private TestService testService;

    @RequestMapping("/")
    public String home(){
        return "Hello World";
    }

    @RequestMapping("/name")
    public String name(){
        return configBean.getName() + " " + configBean.getWant() + " " + configBean.getRandom();
    }

    @RequestMapping("/test")
    public String test(){
        return testService.getName();
    }
}
