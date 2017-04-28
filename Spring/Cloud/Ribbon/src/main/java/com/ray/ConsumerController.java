package com.ray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 2017/4/28 16:08
 */
@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    //消费者去请求，Ribbon在客户端实现了对服务调用的负载均衡
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(){

        return restTemplate.getForEntity("http://compute-service/add?a=10&b=20",String.class).getBody();
    }



}
