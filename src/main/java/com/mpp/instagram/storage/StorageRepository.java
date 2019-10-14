package com.mpp.instagram.storage;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StorageRepository extends CrudRepository<PostEntity, Long> {
    @Query(allowFiltering = true)
    public List<PostEntity> findByUsername(String username);
}
