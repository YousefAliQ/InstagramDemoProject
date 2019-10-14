package com.mpp.instagram.comments.controller;

// comment ..
public class commentsController {

//    private final StorageService storageService;
//
//    public commentsController(StorageService storageService) {
//        this.storageService = storageService;
//    }

//    @PostMapping("/comment")
//    public String AddComments(Map<String,?> input) {
//        String getId = input.get("post_id").toString();
//        String comment = input.get("comment").toString();
//        long post_id = Long.parseLong(getId);
//        Set<commentsEntity> comments = storageService.getComments(post_id);
//        long comment_id = UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE;
//        commentsEntity newcomment = new commentsEntity(comment_id, post_id, comment);
//        comments.add(newcomment);
//        storageService.addComment(post_id, comments);
//
//        return comment;
//    }
}
