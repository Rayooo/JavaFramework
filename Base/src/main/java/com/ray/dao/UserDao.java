package com.ray.dao;

import com.ray.domain.User;

import java.util.List;

/**
 * 2017/5/2 16:55
 */
public interface UserDao {

    void addUser(User user);

    User getUserById(String id);

    List<User> samePassword(User user);
}
