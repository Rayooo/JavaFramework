package com.ray.service.impl;


import com.ray.service.TestService;
import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl implements TestService {

    @Override
    public String getName() {
        return "This is TestServiceImpl";
    }

}
