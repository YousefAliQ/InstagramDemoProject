package com.mpp.instagram.user.controller;

import com.mpp.instagram.user.entity.UserEntity;
import com.mpp.instagram.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
//    @PostMapping("add")
//    public
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public UserEntity login(@RequestParam("userName") String userName, @RequestParam("password") String password){
        System.out.println("userName: "+userName);
        UserEntity userEntity = new UserEntity();

        return userEntity;
        //return userService.login(userName, password);
    }
}
