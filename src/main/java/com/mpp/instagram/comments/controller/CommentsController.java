package com.mpp.instagram.comments.controller;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.mpp.instagram.comments.entity.CommentsEntity;

@RestController
@CrossOrigin(origins = "*")
public class CommentsController {

	BiFunction<List<CommentsEntity>, String, List<CommentsEntity>> 
	
	FourFunction<List<CommentsEntity>, String , Integer , Integer , List<CommentsEntity>> 
		getAllCommentByUser = (rawList , username , skip , limit) -> 
		rawList.stream()
			.filter(comment -> comment.getUser().getUsername().equals(username))
			.sorted((comment1 , comment2) -> 
						comment1.getDateComment().compareTo(comment2.getDateComment()))
			.limit(limit)
			.skip(skip)
			.collect(Collectors.toList());
	
}
