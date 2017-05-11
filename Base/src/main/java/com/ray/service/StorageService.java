package com.ray.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by Ray on 2017/5/11.
 */
public interface StorageService {

    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll() throws Exception;

    Path load(String filename);

    Resource loadAsResource(String filename) throws Exception;

    void deleteAll();

}
