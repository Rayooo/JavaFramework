package com.ray.controller;

import com.ray.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Ray on 2017/5/30.
 */
@RestController
@RequestMapping("/b")
public class ControllerB {

    @Resource
    private User user;


    @RequestMapping("/b")
    public String b(){
        return user.toString();
    }


}