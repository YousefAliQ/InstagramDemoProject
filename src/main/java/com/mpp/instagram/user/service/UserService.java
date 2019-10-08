package com.mpp.instagram.user.service;

import com.mpp.instagram.user.entity.UserEntity;
import com.mpp.instagram.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo ;

    public UserEntity isUserValid(String username, String password) {
        return null;
    }

    public UserEntity saveDataInToDatabase(UserEntity ent) {
        return userRepo.save(ent);
    }
}
