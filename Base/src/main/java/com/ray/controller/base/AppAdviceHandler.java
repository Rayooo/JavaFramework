package com.ray.controller.base;

import com.ray.util.BaseResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ray on 2017/6/19.
 */
@ControllerAdvice
public class AppAdviceHandler {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public BaseResponse<?> handleException(Exception e, HttpServletRequest request) {
        BaseResponse<?> response = new BaseResponse<>();
        response.setCode(11111);
        response.setMessage("系统错误");
        return response;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(true);
        dataBinder.registerCustomEditor(Date.class,  new CustomDateEditor(dateFormat, true));
    }

}
