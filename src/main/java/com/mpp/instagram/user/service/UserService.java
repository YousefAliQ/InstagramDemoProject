package com.mpp.instagram.user.service;

import com.mpp.instagram.user.entity.UserEntity;
import com.mpp.instagram.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {

   // @Autowired
    UserRepository userRepo ;

    public UserEntity login(String userName, String password){
      // return userRepo.findByUsernameAndPassword(userName, password);
    return null;
    }
}
