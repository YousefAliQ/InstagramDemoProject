package com.mpp.instagram.comments.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mpp.instagram.comments.entity.CommentsEntity;

public class FunctionImp {

	FourFunction<List<CommentsEntity>, String , Integer , Integer , List<CommentsEntity>> 
	getAllCommentByUserAndLimitedCount = (rawList , username , skip , limit) -> 
	rawList.stream()
		.filter(comment -> comment.getUser().getUsername().equals(username))
		.sorted((comment1 , comment2) -> 
					comment1.getDateComment().compareTo(comment2.getDateComment()))
		.limit(limit)
		.skip(skip)
		.collect(Collectors.toList());

	BiFunction<List<CommentsEntity>, String, List<CommentsEntity>> 
	getAllCommentByUser = (rawList , username) -> 
	getAllCommentByUserAndLimitedCount.apply(rawList, username, 0, 20);
	
	BiFunction<List<CommentsEntity> , List<CommentsEntity> , List<CommentsEntity>> 
	combineList = (list1 , list2) -> 
	Stream.concat(list1.stream() , list2.stream())
	.collect(Collectors.toList());
	
	BiFunction<CommentsEntity, HashMap<String , Integer>, Integer> 
	cuteCommentRate = (comment , cuteWords) ->
	(int)Arrays.asList(comment.getDescription().split(" ")).stream()
		.filter(word -> cuteWords.containsKey(word.toLowerCase()))
		.collect(Collectors.groupingBy(Function.identity()))
		.entrySet().stream()
		.map(word -> {
			return word.getValue().size() * cuteWords.get(word.getKey());
		})
		.reduce(Integer::sum).get();
		
	TriFunction<List<CommentsEntity> , HashMap<String , Integer> , Integer , List<CommentsEntity>>
	getTopKCuteComments = (rawList , cuteWords , k) ->
		rawList.stream()
			.collect(HashMap<CommentsEntity , Integer>::new, 
					(m , comment) -> m.put(comment , cuteCommentRate.apply(comment , cuteWords)) ,
					(m , comment) -> {})
		.entrySet().stream()
		.sorted((entry1 , entry2) -> entry1.getValue() - entry2.getValue())
		.limit(k)
		.map(entry -> entry.getKey())
		.collect(Collectors.toList());
		
	BiFunction<CommentsEntity, String, Integer> 
	rateComment = (comment , text) ->
		(int)Arrays.stream(text.split(" "))
			.filter(word -> comment.getDescription().toLowerCase().contains(word.toLowerCase()))
			.count();
		
	FiveFunction<List<CommentsEntity> , String , Date , Date , Integer , List<CommentsEntity>> 
	searchCommentsBetweenDate = (rawList , text , dateStart , dateEnd , k) ->
		rawList.stream()
			.filter(comment -> comment.getDateComment().compareTo(dateStart) >= 0)
			.filter(comment -> comment.getDateComment().compareTo(dateEnd) <= 0)
			.collect(HashMap<CommentsEntity, Integer>::new,
					(hashMap , comment) -> hashMap.put(comment , rateComment.apply(comment , text)) , 
					(hashMap , comment) -> {})
			.entrySet().stream()
			.sorted((entry1 , entry2) -> entry1.getValue().compareTo(entry2.getValue()))
			.limit(k)
			.map(entry -> entry.getKey())
			.collect(Collectors.toList());
		
	BiFunction<List<CommentsEntity> , String , List<CommentsEntity>> 
	searchComments = (rawList , text) -> 
		searchCommentsBetweenDate.apply(rawList, text, new Date(0l), new Date(), 100);
		
	TriFunction<List<CommentsEntity> , String , Date , List<CommentsEntity>> 
	searchCommentsByDate = (rawList , text , date) -> 
		searchCommentsBetweenDate.apply(rawList, text, date, date, 100);
}
