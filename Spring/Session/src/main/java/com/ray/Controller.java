package com.ray;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @RequestMapping("/err")
    public String error(){
        throw new RuntimeException();
//        return "error";
    }

    @RequestMapping("/base")
    public BaseResponse base(){
        BaseResponse<List> response = new BaseResponse<>();
        List<HashMap> list = new ArrayList<>();
        for(int i = 0;i < 10; ++i){
            HashMap<Integer,Integer> map = new HashMap<>();
            map.put(i,i+i);
            list.add(map);
        }
        response.setResult(list);
        return response;
    }

    //处理错误信息
    @ExceptionHandler({Exception.class})
    public BaseResponse errorHandler(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(1);
        baseResponse.setMessage("系统错误");
        return baseResponse;
    }

    //处理传递过来的日期信息
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
