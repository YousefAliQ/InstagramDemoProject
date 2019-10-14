package com.mpp.instagram.post.service;

//import com.mpp.instagram.storage.PostEntity;
import com.mpp.instagram.storage.PostEntity;
import com.mpp.instagram.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServices {
    @Autowired
    private PostRepository postRepo;

    public void addPostEntity(PostEntity post) {
        //postRepo.save(post);
    }
}
