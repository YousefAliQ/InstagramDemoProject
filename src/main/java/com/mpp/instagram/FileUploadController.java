package com.mpp.instagram;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

//import com.mpp.instagram.storage.PostEntity;
import com.mpp.instagram.storage.StorageFileNotFoundException;
import com.mpp.instagram.storage.StorageRepository;
import com.mpp.instagram.storage.StorageService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class FileUploadController {

    @Autowired
    private final StorageService storageService;
    //storage repository created
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/post")
    public String listPostUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAllPost().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFilePost", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadPost";
    }

    //Fetching all the data from database
    @RequestMapping(method = RequestMethod.POST, value = "/getpost",produces = "application/json", consumes = "application/json")
    public String showAllData(@RequestBody Map<String, ?> input) {
        List<String> posts = new ArrayList<>();
        storageService.getUserPosts(input.get("username").toString()).forEach(posts::add);
        //List<PostEntity> data = new ArrayList<>();
        //storageRepository.findAll().forEach(data::add);
        //return "data";//data.toString();
        return posts.toString();
    }

    //End

    @PostMapping(value = "/postdescription" , produces = "application/json" ,consumes = "application/json")
    public String PostDescription(@RequestBody Map<String, ?> postInput) {
        String description = postInput.get("description").toString();
        Long id= UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE;
        LocalDateTime uploadDate = LocalDateTime.now();
        String username = postInput.get("username").toString();
        storageService.addPostEntity(id, description, uploadDate, username);
        return "redirect:/post";
    }

    /* This is just for testing both post and profile but its not needed for profile*/
    @GetMapping("/profile")
    public String listProfileUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAllProfile().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFileProfile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadProfile";
    }

    @GetMapping("/files/post/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFilePost(@PathVariable String filename) {
        String location = "post";
        Resource file = storageService.loadAsResource(filename, location);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/files/profile/{filename:.+}")
    //@ResponseBody
    public ResponseEntity<Resource> serveFileProfile(@PathVariable String filename) {
        String location = "profile";
        Resource file = storageService.loadAsResource(filename, location);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/uploadpost")
    public String handlePostFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        String location = "post";
        storageService.store(file, location);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/post";
    }

    @PostMapping("/uploadprofile")
    public String handleProfileFileUpload(@RequestParam("file") MultipartFile file,
                                  RedirectAttributes redirectAttributes) {
        String location = "profile";
        storageService.store(file, location);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/profile";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
