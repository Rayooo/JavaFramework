package com.ray.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Hello {

    @RequestMapping("/")
    public String home(){
        return "Hello Docker";
    }

}
