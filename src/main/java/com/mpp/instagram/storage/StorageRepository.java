package com.mpp.instagram.storage;

import com.mpp.instagram.post.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends CrudRepository<PostEntity, Long> {
   // Iterable<Object> findByUsername(String username);
}
