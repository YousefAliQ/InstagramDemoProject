package com.mpp.instagram.storage;

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

import com.mpp.instagram.post.entity.PostEntity;
import com.mpp.instagram.post.service.PostServices;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
    public void addPostEntity(Long id, String description, LocalDateTime date, String username) {
        //postUrl += "/"+id;
        PostEntity post = new PostEntity(id, postUrl, date, description, username);
        //storageRepository.save(post);
    }

    @Override
    public List<String> getUserPosts(String username) {
        List<PostEntity> data = new ArrayList<>();
        List<String> userPosts = new ArrayList<>();
       // storageRepository.findByUsername(username).forEach(data::add);
        for(PostEntity p: data) {
            userPosts.add(p.getPostUrl());
        }
        return userPosts;
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

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
        FileSystemUtils.deleteRecursively(profileLocation.toFile());
    }

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
