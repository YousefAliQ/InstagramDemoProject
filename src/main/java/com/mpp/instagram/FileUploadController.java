package com.mpp.instagram;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.mpp.instagram.post.entity.PostReturnData;
import com.mpp.instagram.post.entity.PostWrapper;
import com.mpp.instagram.storage.PostEntity;
import com.mpp.instagram.storage.StorageFileNotFoundException;
import com.mpp.instagram.storage.StorageRepository;
import com.mpp.instagram.storage.StorageService;
import com.mpp.instagram.user.controller.UserController;
import com.mpp.instagram.user.entity.UserEntity;
import com.mpp.instagram.user.repository.UserRepository;
import com.mpp.instagram.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springfox.documentation.spring.web.json.Json;

@CrossOrigin(origins = "*")
@RestController
public class FileUploadController {


    private final StorageService storageService;
    private final UserService userService;
    //storage repository created
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    public FileUploadController(StorageService storageService, UserService userService) {
        this.storageService = storageService;
        this.userService = userService;
    }

    @GetMapping("/post")
    @ApiOperation(value = "For displaying all the posts",
            notes = "Look up in the posts database and loads the url for each post stored in the database",
            response = Json.class)
    public String listPostUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAllPost().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFilePost", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadPost";
    }

    //@GetMapping("/home")
   // public

    //Fetching all the data from database
    @RequestMapping(method = RequestMethod.POST, value = "/getpost",produces = "application/json", consumes = "application/json")
    public Map<String, String> showAllData(@RequestBody Map<String, ?> input) {
        List<PostEntity> posts = new ArrayList<>();
        Map<String, String> result = new HashMap<>();
        storageService.getUserPosts(input.get("username").toString()).forEach(posts::add);
        //List<PostEntity> data = new ArrayList<>();
        //storageRepository.findAll().forEach(data::add);
        //return "data";//data.toString();
        result.put("posts", posts.toString());
        return result;
    }

    //End

    @PostMapping(value = "/postdescription" , produces = "application/json" ,consumes = "application/json")
    @ApiOperation(value = "For displaying all the posts",
            notes = "Look up in the database and gets the url for each post stored in the database",
            response = Json.class)
    public String PostDescription(@RequestBody Map<String, ?> postInput) {
        String description = postInput.get("description").toString();
        Long id= UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE;
        LocalDateTime uploadDate = LocalDateTime.now();
        String username = postInput.get("username").toString();
        //int likeCount = 0;
        storageService.addPostEntity(id, description, uploadDate, username);
        return "redirect:/post";
    }

    /* This is just for testing both post and profile but its not needed for profile*/
    @GetMapping("/profile")
    @ApiOperation(value = "For displaying the profile photo",
            notes = "Look up in the profile database and loads the url for the current profile image stored in the database",
            response = Json.class)
    public String listProfileUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAllProfile().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFileProfile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadProfile";
    }

    @GetMapping("/files/post/{filename:.+}")
    @ResponseBody
    @ApiOperation(value = "For loading the posts",
            notes = "Look up in the posts database and loads the url for the post as a resource which is served to other API's",
            response = Json.class)
    public ResponseEntity<Resource> serveFilePost(@PathVariable String filename) {
        String location = "post";
        Resource file = storageService.loadAsResource(filename, location);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/files/profile/{filename:.+}")
    //@ResponseBody
    @ApiOperation(value = "For loading the profile",
            notes = "Look up in the profile database and loads the url for the profile as a resource which is served to other API's",
            response = Json.class)
    public ResponseEntity<Resource> serveFileProfile(@PathVariable String filename) {
        String location = "profile";
        Resource file = storageService.loadAsResource(filename, location);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    // by Yousef Ali
    @PostMapping(value = "/uploadPost", consumes = {"application/json", "multipart/form-data" } , produces = "application/json")
    @ApiOperation(value = "For uploading the posts",
            notes = "Handles the storage of the posts in the directory and creates the url to be stored in the database",
            response = Json.class)
    public ResponseEntity uploadPost(@ModelAttribute PostWrapper model, @RequestHeader (name="Authorization") String token) {

        System.out.println(model.toString());

        String location = "post";
        String url = null;
        UserController userController = new UserController();
        UserEntity user = userService.isUserActive(UUID.fromString(token));

            url = storageService.storeMultipleFiles(model.getImage(), location);
            url += ";";

        Long id= UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE;
        LocalDateTime uploadDate = LocalDateTime.now();
        storageService.addFullPostEntity(id, model.getDescription(), uploadDate, user.getUsername(), url);
        return ResponseEntity.ok().build();

        //return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
    }


    // by Yousef Ali
    @PostMapping(value = "/refreshProfilePosts", produces = "application/json")
    @ApiOperation(value = "For refresh the posts at the client side",
            notes = "Handles the storage of the posts in the directory and creates the url to be stored in the database",
            response = Json.class)
    public List<PostReturnData> refreshProfilePosts(@RequestHeader (name="Authorization") String token) {

        String location = "post";
        String url = null;
        UserEntity user = userService.isUserActive(UUID.fromString(token));

        List<PostEntity> posts = storageService.getUserPosts(user.getUsername());
        List<PostReturnData> retPosts = new ArrayList<>();
        PostReturnData temp = new PostReturnData();
        for (PostEntity post: posts
             ) {
            temp = new PostReturnData();
            temp.setPost_id(post.getPost_id());
            temp.setPostUrl(post.getPostUrl().replace("\\","/").replace("post",""));
            temp.setUploadDate(post.getUploadDate());
            retPosts.add(temp);
        }
        return retPosts;
    }

    @PostMapping("/sdf")
    @ApiOperation(value = "For refresh the posts at the client side",
            notes = "Handles the storage of the posts in the directory and creates the url to be stored in the database",
            response = Json.class)
    public ResponseEntity handlePostFileUpload(@RequestParam("file") MultipartFile file,
                                                       RedirectAttributes redirectAttributes) {
        String location = "post";
        storageService.store(file, location);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        //return "redirect:/post";
        return ResponseEntity.ok().build();
    }

    @PostMapping("/uploadprofile")
    @ApiOperation(value = "For uploading the profile",
            notes = "Handles the storage of the profile in the directory and creates the url to be stored in the database",
            response = Json.class)
    public ResponseEntity handleProfileFileUpload(@RequestParam("file") MultipartFile file,
                                                          RedirectAttributes redirectAttributes) {
        String location = "profile";
        storageService.store(file, location);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        //return "redirect:/profile";
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}

//Code for Renaming the file
//
//    // the file itself as created by the upload servlet
//    String clientSideFileName = fileInfo.getFileName();
//    File uploadedFile = fileInfo.getFile();
//    File uploadDirectory = uploadedFile.getParentFile();
//
//    // now we want to rename the file in case the user tries to upload a
//    // file with the same name (from another directory)
//    UUID uuid = UUID.randomUUID();
//    String newFileName = "XXXUpload-" + uuid.toString() + ".xml";
//    File newFile = new File(uploadDirectory, newFileName);
//             uploadedFile.renameTo(newFile);