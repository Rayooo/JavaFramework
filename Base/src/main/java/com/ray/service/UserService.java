package com.ray.service;

import com.github.pagehelper.PageInfo;
import com.ray.domain.User;

import java.util.List;

/**
 * 2017/5/2 17:07
 */
public interface UserService {

    void addUser(User user);

    User getUserById(String id);

    PageInfo<User> samePassword(User user);

}
