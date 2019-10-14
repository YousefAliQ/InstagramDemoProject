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

    void addFullPostEntity(Long id, String desc, LocalDateTime uploadDate, String username, String url);

    List<PostEntity> getUserPosts(String username);

//    Integer getPostLikeCount(long postId);

//    Integer addLikeToPost(long postId);

//    Integer deleteLikeFromPost(long postId);

//    Set<commentsEntity> getComments(long id);

//    void addComment(long id, Set<commentsEntity> comment);

//    void deleteAll();

}
