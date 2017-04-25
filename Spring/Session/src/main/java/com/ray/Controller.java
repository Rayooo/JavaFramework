package com.ray;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Create by Ray on 2017/4/25 16:38
 */

@RestController
public class Controller {

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
