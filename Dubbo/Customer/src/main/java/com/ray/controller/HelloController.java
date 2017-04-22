package com.ray.controller;


import com.ray.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aa")
public class HelloController {

    @Autowired
    private TestService testService;


    @RequestMapping("/test")
    public String test(){
        return testService.getName();
    }

}
