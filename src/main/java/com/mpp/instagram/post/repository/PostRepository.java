package com.mpp.instagram.post.repository;

import com.mpp.instagram.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<UserEntity, Long> {

}
