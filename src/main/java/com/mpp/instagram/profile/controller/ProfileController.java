package com.mpp.instagram.profile.controller;

import com.mpp.instagram.profile.entity.ProfileEntity;
import com.mpp.instagram.profile.services.ProfileServices;
import com.mpp.instagram.storage.PostEntity;
import com.mpp.instagram.storage.StorageService;
import com.mpp.instagram.user.controller.UserController;
import com.mpp.instagram.user.entity.UserEntity;
import com.mpp.instagram.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

//import javax.jnlp.FileContents;
import javax.swing.text.html.parser.Parser;
import java.util.*;

@RestController
public class ProfileController {

    @Autowired
    StorageService storageService;

    @Autowired
    ProfileServices profileServices;

    @RequestMapping(method = RequestMethod.POST, value = "profile/addProfileImage", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "For Getting the User Profile Image",
            notes = "Look up for the authenticated username and return the profile image url.",
            response = Json.class)
    public Map<String, String> addProfileImage(@RequestBody Map<String, ?> input) {
        Map<String, String> result = new HashMap<>();
        try {

            UserController userController = new UserController();
            Map<String, ?> user = userController.checkToken(input.get("email").toString());
            // check user auth.
            if (user.get("Result").equals("valid")){
                // get image details
                ProfileEntity profileEntity = profileServices.findByUsername(user.get("username").toString());
                result.put("imageUrl", profileEntity.getProfile_image());

            }else{
                result.put("Result", "invalid");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }


    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(method = RequestMethod.POST, value = "profile/getProfilePosts", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "For Getting the User Posts inside of the profile",
            notes = "Look up for the authenticated username and return the all posts related to specific user.",
            response = Json.class)
    public Map<String, String> getProfilePosts(@RequestBody Map<String, ?> input) {
        Map<String, String> result = new HashMap<>();
        try {
            UserController userController = new UserController();
            Map<String, ?> user = null;
            if (input.get("email") != null)
             user = userController.checkToken(input.get("email").toString());
            if (input.get("login") != null){
                input = ( Map<String, ?>) input.get("login");
                user = userController.checkToken(input.get("username").toString());
            }

            // check user auth.
            if (user.get("Result").equals("valid")){

                List<PostEntity> retList = storageService.getUserPosts(input.get("email").toString());
                result.put("data", "retList");
                // get posts related to the user.
                //FileController fileController = new FileController();
                //String posts = fileController.getUserPosts(user.get("username"));
                //result.put("data", posts);
            }else{
                result.put("Result", "invalid");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "profile/getProfileBio", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "For getting the user's profile information",
            notes = "Look up for the authenticated username and get bio, number of posts, followers, and following.",
            response = Json.class)
    public Map<String, String> getProfileBio(@RequestBody Map<String, ?> input) {

        Map<String, String> result = new HashMap<>();
        try {

            UserController userController = new UserController();
            Map<String, ?> user = userController.checkToken(input.get("email").toString());
            // check user auth.
            if (user.get("Result").equals("valid")){
                // get user profile.

                ProfileEntity profileEntity = profileServices.findByUsername(user.get("username").toString());
                result.put("data", profileEntity.toString());
            }else{
                result.put("Result", "invalid");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
        }

    @RequestMapping(method = RequestMethod.POST, value = "profile/setProfileBio", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "For sitting the user's profile information",
            notes = "Look up for the authenticated username and set bio, number of posts, followers, and following.",
            response = Json.class)
    public Map<String, String> setProfileBio(@RequestBody Map<String, ?> input) {

        Map<String, String> result = new HashMap<>();
        try {

            UserController userController = new UserController();
            Map<String, ?> user = userController.checkToken(input.get("emial").toString());
            // check user auth.
            if (user.get("Result").equals("valid")){
                // set user profile information.

                ProfileEntity profileEntity = profileServices.findByUsername(user.get("username").toString());

                if (user.get("profile_image") != null){
                    profileEntity.setProfile_image(user.get("profile_image").toString());
                }

                if (user.get("bio") != null){
                    profileEntity.setBio(user.get("bio").toString());
                }

                profileServices.save(profileEntity);

                result.put("data", profileEntity.toString());

            }else{
                result.put("Result", "invalid");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }



    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(method = RequestMethod.POST, value = "/profile/getPosts", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "For sitting the user's profile information",
            notes = "Look up for the authenticated username and set bio, number of posts, followers, and following.",
            response = Json.class)
    public Map<String, String> getPosts(@RequestBody Map<String, ?> input) {

        Map<String, String> result = new HashMap<>();
        try {
//            Map<String, String> map = new HashMap<>();
//            map = (Map<String, String>) input.get("token");
            UserController userController = new UserController();
            Map<String, String> user = userController.checkToken(input.get("token").toString());
            // check user auth.
            if (user.get("Result").equals("valid")){
                // set user profile information.
                ArrayList<PostEntity> posts = new ArrayList<>();
                storageService.getUserPosts(user.get("username").toString()).forEach(posts::add);

                result.put("posts",posts.toString());
            }else{
                result.put("Result", "invalid");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }



    //@RequestMapping(method = RequestMethod.POST, value = "/getCounters", consumes = "application/json", produces = "application/json")
        @ApiOperation(value = "For Getting the User information like bio,count of Posts, Followers and Following",
                notes = "Look up for the authenticated username and get bio, number of posts, followers, and following.",
                response = Json.class)
    protected Boolean modifyCounters(String username, String type, String ops) {

        Boolean retVal = false;
        try {
            ProfileEntity profileEntity = profileServices.findByUsername(username);

              if (ops.equals("increment")){
                  if (type == "post")
                  profileEntity.setPost_counter(profileEntity.getPost_counter()+1);
                  if (type == "follower")
                  profileEntity.setFollowers_counter(profileEntity.getFollowers_counter()+1);
                  if (type == "following")
                  profileEntity.setFollowing_counter(profileEntity.getFollowing_counter()+1);
              }else
              {
                  if (type == "post")
                      profileEntity.setPost_counter(profileEntity.getPost_counter()-1);
                  if (type == "follower")
                      profileEntity.setFollowers_counter(profileEntity.getFollowers_counter()-1);
                  if (type == "following")
                      profileEntity.setFollowing_counter(profileEntity.getFollowing_counter()-1);
              }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return retVal;
    }
}
