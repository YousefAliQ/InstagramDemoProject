package com.mpp.instagram.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file, String location);

    Stream<Path> loadAllPost();

    Stream<Path> loadAllProfile();

    Path load(String filename, String location);

    Resource loadAsResource(String filename, String location);

    void deleteAll();

}
