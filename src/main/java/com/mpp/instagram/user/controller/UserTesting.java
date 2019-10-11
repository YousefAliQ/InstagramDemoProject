package com.mpp.instagram.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTesting {

    @RequestMapping(value="/test", method = RequestMethod.GET)
    public String test(){
        return "It's Working dude!";

    }

}
