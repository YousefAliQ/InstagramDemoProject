package com.mpp.instagram.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    @Autowired
    StorageRepository storageRepository;

    private final Path rootLocation;
    private final Path profileLocation;
    private String postUrl;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.profileLocation = Paths.get(properties.getProfileLocation());
    }

    @Override
    public void store(MultipartFile file, String location) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                if(location.equals("profile")) {
                    Files.copy(inputStream, this.profileLocation.resolve(filename),
                            StandardCopyOption.REPLACE_EXISTING);
                }
                else {
                    if(location.equals("post")) {
                        Files.copy(inputStream, this.rootLocation.resolve(filename),
                                StandardCopyOption.REPLACE_EXISTING);
                        postUrl = this.rootLocation + filename;
                    }
                }
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }

    }

    @Override
    public String storeMultipleFiles(MultipartFile file, String location) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String post_link;
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
                post_link = this.rootLocation + filename;
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
        return post_link;
    }

    @Override
    public void addPostEntity(Long id, String description, LocalDateTime date, String username) {
        //postUrl += "/"+id;
        PostEntity post = new PostEntity(id, postUrl, date, description, username);
        storageRepository.save(post);
    }

    @Override
    public void addFullPostEntity(Long id, String desc, LocalDateTime uploadDate, String username, String url) {
        PostEntity post = new PostEntity(id, url, uploadDate, desc, username);
        storageRepository.save(post);
    }

    @Override
    public List<PostEntity> getUserPosts(String username) {
        List<PostEntity> data = new ArrayList<>();
        //List<String> userPosts = new ArrayList<>();
        storageRepository.findByUsername(username).forEach(data::add);
//        for(PostEntity p: data) {
//            userPosts.add(p.getPostUrl());
//        }
        return data;
    }

    @Override
    public Stream<Path> loadAllPost() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

//    @Override
//    public Integer getPostLikeCount(long postId) {
//        PostEntity post = storageRepository.findById(postId).get();
//        int likeCount = post.getLikeCount();
//        return likeCount;
//    }
//
//    @Override
//    public Integer addLikeToPost(long postId) {
//        PostEntity post = storageRepository.findById(postId).get();
//        int likeCount = post.getLikeCount();
//        likeCount++;
//        post.setLikeCount(likeCount);
//        return getPostLikeCount(postId);
//    }
//
//    @Override
//    public Integer deleteLikeFromPost (long postId) {
//        PostEntity post = storageRepository.findById(postId).get();
//        int likeCount = post.getLikeCount();
//        likeCount--;
//        post.setLikeCount(likeCount);
//        return getPostLikeCount(postId);
//    }

//    @Override
//    public Set<commentsEntity> getComments(long id) {
//        PostEntity post = storageRepository.findById(id).get();
//        Set<commentsEntity> comments = post.getComments();
//        return comments;
//    }
//
//    public void addComment(long id, Set<commentsEntity> comments) {
//        PostEntity post = storageRepository.findById(id).get();
//        post.setComments(comments);
//    }

    //This is a test for both post and profile
    @Override
    public Stream<Path> loadAllProfile() {
        try {
            return Files.walk(this.profileLocation, 1)
                    .filter(path -> !path.equals(this.profileLocation))
                    .map(this.profileLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename, String location) {
        if(location.equals("profile")) {
            return profileLocation.resolve(filename);
        }
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename, String location) {
        try {
            Path file = load(filename, location);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

//    @Override
//    public void deleteAll() {
//        FileSystemUtils.deleteRecursively(rootLocation.toFile());
//        FileSystemUtils.deleteRecursively(profileLocation.toFile());
//    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
            Files.createDirectories(profileLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
