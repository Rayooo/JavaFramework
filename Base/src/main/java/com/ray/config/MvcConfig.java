package com.ray.config;

import com.ray.interceptor.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 2017/5/4 11:22
 */
//spring mvc 配置 拦截器
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new Interceptor()).addPathPatterns("/session/*");
        //还可以添加多个拦截器
    }
}
