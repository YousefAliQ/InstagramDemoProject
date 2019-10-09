package com.mpp.instagram.post.repository;

import com.mpp.instagram.post.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
}
