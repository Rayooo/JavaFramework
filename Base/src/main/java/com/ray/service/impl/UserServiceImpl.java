package com.ray.service.impl;

import com.github.pagehelper.PageHelper;
import com.ray.dao.UserDao;
import com.ray.domain.User;
import com.ray.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * 2017/5/2 17:08
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Transactional
    @Override
    public void addUser(User user){
        user.setId(UUID.randomUUID().toString());
        userDao.addUser(user);
    }

    @Override
    public User getUserById(String id){
        return userDao.getUserById(id);
    }

    @Override
    public List samePassword(User user) {
//        PageHelper.startPage(user.getPageNo(),user.getPageSize());
        return userDao.samePassword(user);
    }


}
