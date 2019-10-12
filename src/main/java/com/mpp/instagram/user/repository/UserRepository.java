package com.mpp.instagram.user.repository;

import com.mpp.instagram.user.entity.UserEntity;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @AllowFiltering
    public UserEntity findByUsernameAndPassword(String username, String password);

    UserEntity findByUsername(String username);

    @AllowFiltering
    UserEntity findByUserToken(UUID token);

}
