package com.ray.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 2017/5/3 10:09
 */
@RestController
@RequestMapping("/session")
public class SessionTestController {

    @RequestMapping("/set")
    public String set(HttpServletRequest request){
        request.getSession().setAttribute("testKey","testValue");
        return "设置session testKey,testValue";
    }

    @RequestMapping("/query")
    public String query(HttpServletRequest request){
        Object value = request.getSession().getAttribute("testKey");
        return "查询 Session testKey = " + value;
    }



}
