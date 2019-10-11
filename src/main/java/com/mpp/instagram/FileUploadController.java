package com.mpp.instagram;

import java.io.IOException;
import java.util.stream.Collectors;

import com.mpp.instagram.storage.StorageFileNotFoundException;
import com.mpp.instagram.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

    private final StorageService storageService;

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
