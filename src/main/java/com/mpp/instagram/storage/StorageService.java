package com.mpp.instagram.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file, String location);

    String storeMultipleFiles(MultipartFile file, String location);

    Stream<Path> loadAllPost();

    Stream<Path> loadAllProfile();

    Path load(String filename, String location);

    Resource loadAsResource(String filename, String location);

    void addPostEntity(Long id, String desc, LocalDateTime uploadDate, String username);

    PostEntity addFullPostEntity(Long id, String desc, LocalDateTime uploadDate, String username, String ur);

    List<PostEntity> getUserPosts(String username);

    void deleteAll();

}
