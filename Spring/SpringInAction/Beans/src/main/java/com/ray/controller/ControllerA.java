package com.ray.controller;

import com.ray.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Ray on 2017/5/30.
 */
@RestController
@RequestMapping("/a")
public class ControllerA {


    private User user;

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }

    @RequestMapping("/a")
    public String a(){
        return user.toString();
    }


}
