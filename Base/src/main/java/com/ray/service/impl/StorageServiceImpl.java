package com.ray.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.ray.service.StorageService;
import com.ray.util.OSSUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Created by Ray on 2017/5/11
 */

@Service("storageService")
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;

//    @Autowired
//    private OSSClient ossClient;

    @Autowired
    public StorageServiceImpl() {
        this.rootLocation = Paths.get("/Users/Ray/Documents/GitHub/JavaFramework/Base/target/classes/static/uploadFile");
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if(file.isEmpty()){
                System.out.println("文件找不到");
            }
            //Common io 中获取后缀名
            //String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String newFileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.rootLocation.resolve(newFileName));

            //bucketName, 文件名, 文件
            //PutObjectResult result = ossClient.putObject(OSSUtil.getBucketName(), newFileName, file.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stream<Path> loadAll() throws Exception {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation));
        } catch (IOException e) {
            throw new Exception();
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) throws Exception {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource((file.toUri()));
            if(resource.exists() || resource.isReadable()){
                return resource;
            }
            else{
                throw new Exception();
            }
        }catch (MalformedURLException e){
            throw new Exception();
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
