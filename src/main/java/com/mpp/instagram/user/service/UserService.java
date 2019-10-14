package com.mpp.instagram.user.service;

import com.mpp.instagram.user.entity.UserEntity;
import com.mpp.instagram.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo ;

    public UserEntity isUserValid(String username, String password) {
        return userRepo.findByUsernameAndPassword(username,password);
    }

    public UserEntity isUserActive(UUID token) {
        return userRepo.findByUserToken(token);
        //return null;
    }

    public void saveDataInToDatabase(Map<String ,?> input) {
        //Generating Random UUID Everytime Inserting into database
        Long id=UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE;
        UserEntity ent = new UserEntity((String) input.get("username"),(String) input.get("fullname"),(String) input.get("email"),(String) input.get("password"));
        ent.setId(id);
        //Saving User Entity object in Cassandra database
         userRepo.save(ent);
    }
    //Showing all records Inserting into Database
    public List<UserEntity> listAll() {
        List<UserEntity> records = new ArrayList<>();
        userRepo.findAll().forEach(records::add); //fun with Java 8
        return records;
    }

    public UserEntity findByUsername(String username) {
         UserEntity user=userRepo.findByUsername(username);
        return user;
    }

    public UserEntity findByEmail(String email) {
        UserEntity user=userRepo.findByEmail(email);
        return user;
    }


    public UserEntity saveToken(UserEntity userEntity){

        return userRepo.save(userEntity);
    }

}
