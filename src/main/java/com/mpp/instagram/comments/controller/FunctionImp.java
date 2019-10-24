package com.mpp.instagram.comments.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.mpp.instagram.comments.entity.CommentsEntity;

public class FunctionImp {

	public static final FourFunction<List<CommentsEntity>, String , Integer , Integer , List<CommentsEntity>> 
	getAllCommentByUserAndLimitedCount = (rawList , username , skip , limit) -> 
	rawList.stream()
		.filter(comment -> comment.getUser().getUsername().equals(username))
		.sorted((comment2 , comment1) -> 
					comment1.getDateComment().compareTo(comment2.getDateComment()))
		.limit(limit)
		.skip(skip)
		.collect(Collectors.toList());

	public static final BiFunction<List<CommentsEntity>, String, List<CommentsEntity>> 
	getAllCommentByUser = (rawList , username) -> 
	getAllCommentByUserAndLimitedCount.apply(rawList, username, 0, 20);
	
	public static final BiFunction<List<CommentsEntity> , List<CommentsEntity> , List<CommentsEntity>> 
	combineList = (list1 , list2) -> 
	Stream.concat(list1.stream() , list2.stream())
	.collect(Collectors.toList());
	
	public static final BiFunction<CommentsEntity, HashMap<String , Integer>, Integer> 
	cuteCommentRate = (comment , cuteWords) ->
	(int)Arrays.asList(comment.getDescription().split(" ")).stream()
		.filter(word -> cuteWords.containsKey(word.toLowerCase()))
		.collect(Collectors.groupingBy(Function.identity()))
		.entrySet().stream()
		.map(word -> {
			return word.getValue().size(); //* cuteWords.get(word.getKey())  
		})
		.collect(Collectors.summingInt(Integer::intValue));
		
	public static final TriFunction<List<CommentsEntity> , HashMap<String , Integer> , Integer , List<CommentsEntity>>
	getTopKCuteComments = (rawList , cuteWords , k) ->
		rawList.stream()
			.collect(HashMap<CommentsEntity , Integer>::new, 
					(m , comment) -> m.put(comment , cuteCommentRate.apply(comment , cuteWords)) ,
					(m , comment) -> {})
		.entrySet().stream()
		.sorted((entry2 , entry1) -> entry1.getValue() - entry2.getValue())
		.limit(k)
		.map(entry -> entry.getKey())
		.collect(Collectors.toList());
		
	public static final BiFunction<CommentsEntity, String, Integer> 
	rateComment = (comment , text) ->
		(int)Arrays.stream(text.split(" "))
			.filter(word -> comment.getDescription().toLowerCase().contains(word.toLowerCase()))
			.count();
		
	public static final FiveFunction<List<CommentsEntity> , String , LocalDateTime , LocalDateTime , Integer , List<CommentsEntity>> 
	searchCommentsBetweenDate = (rawList , text , dateStart , dateEnd , k) ->
		rawList.stream()
			.filter(comment -> comment.getDateComment().isAfter(dateStart) || comment.getDateComment().equals(dateStart) )
			.filter(comment -> comment.getDateComment().isBefore(dateEnd) || comment.getDateComment().equals(dateEnd))
			.collect(HashMap<CommentsEntity, Integer>::new,
					(hashMap , comment) -> hashMap.put(comment , rateComment.apply(comment , text)) , 
					(hashMap , comment) -> {})
			.entrySet().stream()
			.filter(entry -> entry.getValue() > 0)
			.sorted((entry1 , entry2) -> entry2.getValue().compareTo(entry1.getValue()))
			.limit(k)
			.map(entry -> entry.getKey())
			.collect(Collectors.toList());
		
	public static final BiFunction<List<CommentsEntity> , String , List<CommentsEntity>> 
	searchComments = (rawList , text) -> 
		searchCommentsBetweenDate.apply(rawList, text, LocalDateTime.of(2000, 01, 1, 00, 00) , LocalDateTime.now(), 100);
		
	public static final TriFunction<List<CommentsEntity> , String , LocalDateTime , List<CommentsEntity>> 
	searchCommentsByDate = (rawList , text , date) -> 
		searchCommentsBetweenDate.apply(rawList, text, date, date, 100);
		
	public static final Function<String[], HashMap<String , Integer>> 
	wordsToMap = (entries) -> 
		IntStream.rangeClosed(1, entries.length).boxed()
			.collect(HashMap<String , Integer>::new , 
					(map , i) -> map.put(entries[i-1] , i ), 
					(map , i) -> {});
		
}
