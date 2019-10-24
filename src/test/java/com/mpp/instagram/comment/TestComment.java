package com.mpp.instagram.comment;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.mpp.instagram.comments.controller.CuteWords;
import com.mpp.instagram.comments.controller.FunctionImp;
import com.mpp.instagram.comments.entity.CommentsEntity;
import com.mpp.instagram.storage.PostEntity;
import com.mpp.instagram.user.entity.UserEntity;

public class TestComment {
	
	@Test
	public void testGetAllCommentByUserAndLimitedCount() {

		UserEntity mickael = new UserEntity("Mickael");
		UserEntity adnan = new UserEntity("Adnan");
		UserEntity bubacar = new UserEntity("Bubacar");

		PostEntity post1 = new PostEntity(1, "url1", LocalDateTime.of(2019, 10, 20, 00, 50), "desc1", "Mickael");
		PostEntity post2 = new PostEntity(2, "url2", LocalDateTime.of(2019, 10, 20, 9, 50), "desc2", "Adnan");
		
		List<CommentsEntity> comments = new ArrayList<CommentsEntity>();
		CommentsEntity com1 = new CommentsEntity(mickael, post1, "some dummy text for comment 1 post 1", post1.getUploadDate().plusMinutes(5));
		CommentsEntity com2 = new CommentsEntity(adnan, post1, "some dummy text for comment 2 post 1", post1.getUploadDate().plusMinutes(10));
		CommentsEntity com3 = new CommentsEntity(mickael, post1, "some dummy text for comment 3 post 1", post1.getUploadDate().plusMinutes(15));
		CommentsEntity com4 = new CommentsEntity(bubacar, post1, "some dummy text for comment 4 post 1", post1.getUploadDate().plusMinutes(20));
		CommentsEntity com5 = new CommentsEntity(adnan, post1, "some dummy text for comment 5 post 1", post1.getUploadDate().plusMinutes(25));
		CommentsEntity com6 = new CommentsEntity(mickael, post1, "some dummy text for comment 6 post 1", post1.getUploadDate().plusMinutes(30));
		CommentsEntity com7 = new CommentsEntity(adnan, post2, "some dummy text for comment 1 post 2", post2.getUploadDate().plusMinutes(25));
		CommentsEntity com8 = new CommentsEntity(mickael, post1, "some dummy text for comment 2 post 2", post2.getUploadDate().plusMinutes(30));
		CommentsEntity com9 = new CommentsEntity(adnan, post1, "some dummy text for comment 3 post 2", post2.getUploadDate().plusMinutes(25));
		CommentsEntity com10 = new CommentsEntity(bubacar, post1, "some dummy text for comment 4 post 2", post2.getUploadDate().plusMinutes(30));
		comments.add(com1);
		comments.add(com2);
		comments.add(com3);
		comments.add(com4);
		comments.add(com5);
		comments.add(com6);
		comments.add(com7);
		comments.add(com8);
		comments.add(com9);
		comments.add(com10);
		
		List<CommentsEntity> actual = FunctionImp.getAllCommentByUserAndLimitedCount.apply(comments, mickael.getUsername(), 0 , 3);
		List<CommentsEntity> expected = new ArrayList<>();
		expected.add(com8);
		expected.add(com6);
		expected.add(com3);
		
		assertEquals(expected, actual);
		
		actual = FunctionImp.getAllCommentByUser.apply(comments, mickael.getUsername());
		expected.add(com1);
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testCombineList() {

		UserEntity mickael = new UserEntity("Mickael");
		UserEntity adnan = new UserEntity("Adnan");
		UserEntity bubacar = new UserEntity("Bubacar");

		PostEntity post1 = new PostEntity(1, "url1", LocalDateTime.of(2019, 10, 20, 00, 50), "desc1", "Mickael");
		PostEntity post2 = new PostEntity(2, "url2", LocalDateTime.of(2019, 10, 20, 9, 50), "desc2", "Adnan");
		
		List<CommentsEntity> comments1 = new ArrayList<CommentsEntity>();
		CommentsEntity com1 = new CommentsEntity(mickael, post1, "some dummy text for comment 1 post 1", post1.getUploadDate().plusMinutes(5));
		CommentsEntity com2 = new CommentsEntity(adnan, post1, "some dummy text for comment 2 post 1", post1.getUploadDate().plusMinutes(10));
		CommentsEntity com3 = new CommentsEntity(mickael, post1, "some dummy text for comment 3 post 1", post1.getUploadDate().plusMinutes(15));
		CommentsEntity com4 = new CommentsEntity(bubacar, post1, "some dummy text for comment 4 post 1", post1.getUploadDate().plusMinutes(20));
		CommentsEntity com5 = new CommentsEntity(adnan, post1, "some dummy text for comment 5 post 1", post1.getUploadDate().plusMinutes(25));
		CommentsEntity com6 = new CommentsEntity(mickael, post1, "some dummy text for comment 6 post 1", post1.getUploadDate().plusMinutes(30));
		CommentsEntity com7 = new CommentsEntity(adnan, post2, "some dummy text for comment 1 post 2", post2.getUploadDate().plusMinutes(25));
		CommentsEntity com8 = new CommentsEntity(mickael, post1, "some dummy text for comment 2 post 2", post2.getUploadDate().plusMinutes(30));
		CommentsEntity com9 = new CommentsEntity(adnan, post1, "some dummy text for comment 3 post 2", post2.getUploadDate().plusMinutes(25));
		CommentsEntity com10 = new CommentsEntity(bubacar, post1, "some dummy text for comment 4 post 2", post2.getUploadDate().plusMinutes(30));
		comments1.add(com1);
		comments1.add(com2);
		comments1.add(com3);
		comments1.add(com4);
		comments1.add(com5);
		
		List<CommentsEntity> comments2 = new ArrayList<CommentsEntity>();
		comments2.add(com6);
		comments2.add(com7);
		comments2.add(com8);
		comments2.add(com9);
		comments2.add(com10);
		
		List<CommentsEntity> actual = FunctionImp.combineList.apply(comments1, comments2);
		List<CommentsEntity> expected = new ArrayList<>();
		expected.addAll(comments1);
		expected.addAll(comments2);
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testCuteCommentRate() {

		UserEntity mickael = new UserEntity("Mickael");
		PostEntity post1 = new PostEntity(1, "url1", LocalDateTime.of(2019, 10, 20, 00, 50), "desc1", "Mickael");
		CommentsEntity comment = new CommentsEntity(mickael, post1, "Hey baby!!! you look so cute and very lovely today. I love you sweet heart ", post1.getUploadDate().plusMinutes(30));
		
		Integer actual = FunctionImp.cuteCommentRate.apply(comment, CuteWords.getInstance());
		Integer expected = 5;
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testWordToMap() {

		String[] cuteWords = {"love" , "lovely" , "honey"};
		
		HashMap<String , Integer> actual = FunctionImp.wordsToMap.apply(cuteWords);
		HashMap<String , Integer> expected = new HashMap<>();
		expected.put("love", 1);
		expected.put("lovely", 2);
		expected.put("honey", 3);
		
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testGetTopKCuteComments() {

		UserEntity mickael = new UserEntity("Mickael");
		UserEntity adnan = new UserEntity("Adnan");
		UserEntity bubacar = new UserEntity("Bubacar");

		PostEntity post1 = new PostEntity(1, "url1", LocalDateTime.of(2019, 10, 20, 00, 50), "desc1", "Mickael");
		PostEntity post2 = new PostEntity(2, "url2", LocalDateTime.of(2019, 10, 20, 9, 50), "desc2", "Adnan");
		
		List<CommentsEntity> comments = new ArrayList<CommentsEntity>();
		CommentsEntity com1 = new CommentsEntity(mickael, post1, "We all love Pizza", post1.getUploadDate().plusMinutes(5));
		CommentsEntity com2 = new CommentsEntity(adnan, post1, "The girl who seated next to me was so cute . I think I am in love with her. Kisses ", post1.getUploadDate().plusMinutes(10));
		CommentsEntity com3 = new CommentsEntity(mickael, post1, "I am hungry", post1.getUploadDate().plusMinutes(15));
		CommentsEntity com4 = new CommentsEntity(bubacar, post1, "I want to pass MPP!!", post1.getUploadDate().plusMinutes(20));
		CommentsEntity com5 = new CommentsEntity(adnan, post1, "Let's take some rest", post1.getUploadDate().plusMinutes(25));
		CommentsEntity com6 = new CommentsEntity(mickael, post1, "Hey baby!!! you look so cute and very lovely today. I love you sweet heart ", post1.getUploadDate().plusMinutes(30));
		CommentsEntity com7 = new CommentsEntity(adnan, post2, "some dummy text for comment 1 post 2", post2.getUploadDate().plusMinutes(25));
		CommentsEntity com8 = new CommentsEntity(mickael, post1, "another dummy text for comment 2 post 2", post2.getUploadDate().plusMinutes(30));
		CommentsEntity com9 = new CommentsEntity(adnan, post1, "Food is good", post2.getUploadDate().plusMinutes(25));
		CommentsEntity com10 = new CommentsEntity(bubacar, post1, "I am in a very good mood", post2.getUploadDate().plusMinutes(30));
		comments.add(com1);
		comments.add(com2);
		comments.add(com3);
		comments.add(com4);
		comments.add(com5);
		comments.add(com6);
		comments.add(com7);
		comments.add(com8);
		comments.add(com9);
		comments.add(com10);
		
		List<CommentsEntity> actual = FunctionImp.getTopKCuteComments.apply(comments, CuteWords.getInstance(), 3);
		List<CommentsEntity> expected = new ArrayList<>();
		expected.add(com6);
		expected.add(com2);
		expected.add(com1);
		
		assertEquals(expected, actual);
		
		/*assertEquals(expected.stream().map(m -> m.getDescription()).collect(Collectors.toList()), 
				actual.stream().map(m -> m.getDescription()).collect(Collectors.toList()));*/
		
	}
	
	@Test
	public void testRateComment() {

		UserEntity mickael = new UserEntity("Mickael");
		PostEntity post1 = new PostEntity(1, "url1", LocalDateTime.of(2019, 10, 20, 00, 50), "desc1", "Mickael");
		CommentsEntity comment = new CommentsEntity(mickael, post1, "Hey baby!!! you look so cute and very lovely today. I love you sweet heart ", post1.getUploadDate().plusMinutes(30));
		
		Integer actual = FunctionImp.rateComment.apply(comment, "lovely today heart");
		Integer expected = 3;
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testSearchCommentsBetweenDate() {

		UserEntity mickael = new UserEntity("Mickael");
		UserEntity adnan = new UserEntity("Adnan");
		UserEntity bubacar = new UserEntity("Bubacar");

		PostEntity post1 = new PostEntity(1, "url1", LocalDateTime.of(2019, 10, 20, 00, 50), "desc1", "Mickael");
		PostEntity post2 = new PostEntity(2, "url2", LocalDateTime.of(2019, 10, 20, 9, 50), "desc2", "Adnan");
		
		List<CommentsEntity> comments = new ArrayList<CommentsEntity>();
		CommentsEntity com1 = new CommentsEntity(mickael, post1, "We all love Pizza", post1.getUploadDate().plusMinutes(5));
		CommentsEntity com2 = new CommentsEntity(adnan, post1, "The girl who seated next to me was so cute . I think I am in love with her. Kisses ", post1.getUploadDate().plusMinutes(10));
		CommentsEntity com3 = new CommentsEntity(mickael, post1, "I am hungry", post1.getUploadDate().plusMinutes(15));
		CommentsEntity com4 = new CommentsEntity(bubacar, post1, "I want to pass MPP!!", post1.getUploadDate().plusMinutes(20));
		CommentsEntity com5 = new CommentsEntity(adnan, post1, "Let's take some rest", post1.getUploadDate().plusMinutes(25));
		CommentsEntity com6 = new CommentsEntity(mickael, post1, "Hey baby!!! you look so cute and very lovely today. I love you sweet heart ", post1.getUploadDate().plusMinutes(30));
		CommentsEntity com7 = new CommentsEntity(adnan, post2, "some dummy text for comment 1 post 2", post2.getUploadDate().plusMinutes(25));
		CommentsEntity com8 = new CommentsEntity(mickael, post1, "another dummy text for comment 2 post 2", post2.getUploadDate().plusMinutes(30));
		CommentsEntity com9 = new CommentsEntity(adnan, post1, "Food is good", post2.getUploadDate().plusMinutes(25));
		CommentsEntity com10 = new CommentsEntity(bubacar, post1, "I am in a very good mood", post2.getUploadDate().plusMinutes(30));
		comments.add(com1);
		comments.add(com2);
		comments.add(com3);
		comments.add(com4);
		comments.add(com5);
		comments.add(com6);
		comments.add(com7);
		comments.add(com8);
		comments.add(com9);
		comments.add(com10);
		
		List<CommentsEntity> actual = FunctionImp.searchCommentsBetweenDate.apply(comments, "love today heart" , LocalDateTime.of(2019, 10, 20, 00, 55), LocalDateTime.of(2019, 10, 20, 01, 30), 10 );
		List<CommentsEntity> expected = new ArrayList<>();
		expected.add(com6);
		expected.add(com2);
		expected.add(com1);
		
		assertEquals(expected , actual);
		
	}
	
	@Test
	public void testSearchComments() {

		UserEntity mickael = new UserEntity("Mickael");
		UserEntity adnan = new UserEntity("Adnan");
		UserEntity bubacar = new UserEntity("Bubacar");

		PostEntity post1 = new PostEntity(1, "url1", LocalDateTime.of(2019, 10, 20, 00, 50), "desc1", "Mickael");
		PostEntity post2 = new PostEntity(2, "url2", LocalDateTime.of(2019, 10, 20, 9, 50), "desc2", "Adnan");
		
		List<CommentsEntity> comments = new ArrayList<CommentsEntity>();
		CommentsEntity com1 = new CommentsEntity(mickael, post1, "We all love Pizza", post1.getUploadDate().plusMinutes(5));
		CommentsEntity com2 = new CommentsEntity(adnan, post1, "The girl who seated next to me was so cute . I think I am in love with her. Kisses ", post1.getUploadDate().plusMinutes(10));
		CommentsEntity com3 = new CommentsEntity(mickael, post1, "I am hungry", post1.getUploadDate().plusMinutes(15));
		CommentsEntity com4 = new CommentsEntity(bubacar, post1, "I want to pass MPP!!", post1.getUploadDate().plusMinutes(20));
		CommentsEntity com5 = new CommentsEntity(adnan, post1, "Let's take some rest", post1.getUploadDate().plusMinutes(25));
		CommentsEntity com6 = new CommentsEntity(mickael, post1, "Hey baby!!! you look so cute and very lovely today. I love you sweet heart ", post1.getUploadDate().plusMinutes(30));
		CommentsEntity com7 = new CommentsEntity(adnan, post2, "some dummy text for comment 1 post 2", post2.getUploadDate().plusMinutes(25));
		CommentsEntity com8 = new CommentsEntity(mickael, post1, "another dummy text for comment 2 post 2", post2.getUploadDate().plusMinutes(30));
		CommentsEntity com9 = new CommentsEntity(adnan, post1, "Food is good", post2.getUploadDate().plusMinutes(25));
		CommentsEntity com10 = new CommentsEntity(bubacar, post1, "I am in a very good mood", post2.getUploadDate().plusMinutes(30));
		comments.add(com1);
		comments.add(com2);
		comments.add(com3);
		comments.add(com4);
		comments.add(com5);
		comments.add(com6);
		comments.add(com7);
		comments.add(com8);
		comments.add(com9);
		comments.add(com10);
		
		List<CommentsEntity> actual = FunctionImp.searchComments.apply(comments, "love today heart" );
		List<CommentsEntity> expected = new ArrayList<>();
		expected.add(com6);
		expected.add(com2);
		expected.add(com1);
		
		assertEquals(expected, actual);
		
	}
	
}
