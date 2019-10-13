package com.mpp.instagram.storage;

import com.mpp.instagram.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends CrudRepository<UserEntity, Long> {
    Iterable<Object> findByUsername(String username);
}
