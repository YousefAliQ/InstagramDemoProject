package com.mpp.instagram.post.controller;

import com.mpp.instagram.post.entity.PostEntity;
import com.mpp.instagram.post.service.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostServices services;

    @PostMapping("create")
    public PostEntity addPost(@RequestBody PostEntity post){
        return services.createPost(post);
    }
}
