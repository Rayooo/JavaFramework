package com.ray.controller;

import com.github.pagehelper.PageInfo;
import com.ray.controller.base.BaseController;
import com.ray.domain.User;
import com.ray.service.UserService;
import com.ray.util.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 2017/5/2 16:08
 */

@RestController
@RequestMapping("/")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @RequestMapping("/")
    public String home(){
        return "start success";
    }

    @RequestMapping("/add")
    public BaseResponse add(User user, HttpServletRequest request){
        userService.addUser(user);
        return new BaseResponse();
    }

    @RequestMapping("/getUser")
    public BaseResponse<User> getUser(String id, HttpServletRequest request){
        BaseResponse<User> response = new BaseResponse<>();
        response.setResult(userService.getUserById(id));
        return response;
    }

    @RequestMapping("/samePassword")
    public BaseResponse<PageInfo<User>> samePassword(User user) {
        BaseResponse<PageInfo<User>> response = new BaseResponse<>();
        response.setResult(userService.samePassword(user));
        return response;
    }
}
