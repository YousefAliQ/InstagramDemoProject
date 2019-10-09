package com.mpp.instagram.user.service;

import com.mpp.instagram.user.entity.UserEntity;
import com.mpp.instagram.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo ;

    public UserEntity isUserValid(String username, String password) {
        return userRepo.findByUsernameAndPassword(username,password);
    }

    public UserEntity saveDataInToDatabase(UserEntity ent) {
        return userRepo.save(ent);
    }
    //Showing all records Inserting into Database
    public List<UserEntity> listAll() {
        List<UserEntity> products = new ArrayList<>();
        userRepo.findAll().forEach(products::add); //fun with Java 8
        return products;
    }
}
