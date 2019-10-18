package com.mpp.instagram.comments.repository;

import com.mpp.instagram.comments.entity.commentsEntity;
import org.springframework.data.repository.CrudRepository;

public interface commentsRepository extends CrudRepository<commentsEntity, Long> {
}
