package com.mpp.instagram.post.service;

import com.mpp.instagram.post.entity.PostEntity;
import com.mpp.instagram.post.repository.PostRepository;
import com.mpp.instagram.storage.IMultimedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServices implements IMultimedia {
    @Autowired
    private PostRepository repository;

    @Override
    public String formatUrl(String fileName) {
        return null;
    }

    @Override
    public String getFormulatedUrls() {
        return null;
    }

    public PostEntity createPost(PostEntity post) {
        return repository.save(post);
    }
}
