package com.ray.controller;

import com.ray.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class demoController {

    @Resource
    private UserService userService;

    @RequestMapping("/{name}")
    public String home(@PathVariable(value = "name") String name){
        return userService.sayHello(name);
    }

}
