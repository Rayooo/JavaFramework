package com.ray.util;

import com.aliyun.oss.OSSClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.util.Date;

/**
 * 2017/5/3 16:20
 */
@Configuration
public class OSSUtil {

    private static final String endPoint = "oss-cn-shanghai.aliyuncs.com";
    // accessKey请登录https://ak-console.aliyun.com/#/查看
    private static final String accessKeyId = "";

    private static final String accessKeySecret = "";

    private static final String bucketName = "";

    private volatile static OSSClient ossClient;

    @Bean(name = "OSSClient")        //不过spring默认单例模式，这些有点多余
    public static OSSClient getOssClient(){
        if(ossClient == null){
            synchronized (OSSUtil.class){
                if(ossClient == null){
                    ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
                }
            }
        }
        return ossClient;
    }

    public static String getEndPoint() {
        return endPoint;
    }

    public static String getAccessKeyId() {
        return accessKeyId;
    }

    public static String getAccessKeySecret() {
        return accessKeySecret;
    }

    public static String getBucketName() {
        return bucketName;
    }

    public static String generateUrl(String fileName){
        Date expiration = new Date(new Date().getTime() + 3600*1000);   //过期时间为1小时
        URL url = getOssClient().generatePresignedUrl(bucketName, fileName, expiration);
        return url.toString();
    }
}
