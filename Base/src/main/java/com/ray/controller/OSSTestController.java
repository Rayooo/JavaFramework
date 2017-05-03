package com.ray.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.PutObjectResult;
import com.ray.util.OSSUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 *  2017/5/3 16:34
 */
@RestController
@RequestMapping("/oss")
public class OSSTestController {

    @Resource
    private OSSClient ossClient;

    @RequestMapping("/listBucket")
    public String addBucket(){
        List<Bucket> buckets = ossClient.listBuckets();
        buckets.forEach(System.out::println);
        return "success";
    }

    @RequestMapping("/add")
    public String add() throws FileNotFoundException {
        File file = new File("/Users/Ray/Documents/GitHub/JavaFramework/Base/src/main/java/com/ray/controller/OSSTestController.java");

        // test_1 类似文件夹系统
        String fileName = "test_1/"+UUID.randomUUID().toString()+ "-"+file.getName();

        //bucketName, 文件名, 文件
        PutObjectResult result = ossClient.putObject(OSSUtil.getBucketName(), fileName, file);

        return OSSUtil.generateUrl(fileName);
    }

}
