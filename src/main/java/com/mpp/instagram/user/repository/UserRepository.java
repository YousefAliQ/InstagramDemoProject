package com.mpp.instagram.user.repository;

import com.mpp.instagram.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    //public UserEntity findByUsernameAndPassword(String username, String password);
}
