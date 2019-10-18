package com.mpp.instagram.storage;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends CrudRepository<PostEntity, Long> {
   // Iterable<Object> findByUsername(String username);
    @Query(allowFiltering = true)
    List<PostEntity> findByUsername(String username);
}
