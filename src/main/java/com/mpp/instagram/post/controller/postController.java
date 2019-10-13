package com.mpp.instagram.post.controller;

import com.mpp.instagram.post.entity.PostEntity;
import com.mpp.instagram.profile.entity.ProfileEntity;
import com.mpp.instagram.user.controller.UserController;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*", maxAge = 3600)
public class postController {

    @RequestMapping(method = RequestMethod.POST, value = "/posts", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "For getting the user's profile information",
            notes = "Look up for the authenticated username and get bio, number of posts, followers, and following.",
            response = Json.class)
    public Map<String, List<PostEntity>> posts(@RequestBody Map<String, ?> input,
                                        @RequestHeader(value = "Authorization" ) String auth) {

        Map<String, List<PostEntity>> result = new HashMap<>();
        try {
            UserController userController = new UserController();
            Map<String, ?> user = userController.checkToken(auth);
            // check user auth.
            if (user.get("Result").equals("valid")){

                // TODO: send back all the posts
                List<PostEntity> posts = new ArrayList<>();

                result.put("data", posts);
                // get user profile.
                //ProfileEntity profileEntity = profileServices.findByUsername(user.get("username").toString());
                //result.put("data", profileEntity.toString());
            }else{
                result.put("Result", new ArrayList<PostEntity>());
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

}
