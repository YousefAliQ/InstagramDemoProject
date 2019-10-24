package com.mpp.instagram.Post;

import com.mpp.instagram.Data.AllData;
import com.mpp.instagram.FunctionalCode.Posts.PostService;
import com.mpp.instagram.Models.Comments;
import com.mpp.instagram.Models.Likes;
import com.mpp.instagram.Models.Post;
import com.mpp.instagram.Models.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostServiceTest {

    private  PostService postService = new PostService();

    @Before
    public void init(){
        postService = new PostService();
        AllData d = new AllData();
        d.fillData();
    }

    @Test
    public void getTopKMostLikelyFriendSuggestions() {
        User target = AllData.users.get(0);
        List<User> allUsers = AllData.users;

        List<User> expected = new ArrayList<>();
        expected.add(AllData.users.get(4));
        expected.add(AllData.users.get(1));
        expected.add(AllData.users.get(2));
        List<User> actual= postService.getTopKMostLikelyFriendSuggestion(allUsers,target,3);
        Assert.assertEquals(actual, expected);

    }

    @Test
    public void getTopKLatestPost() {
        LocalDateTime date = LocalDateTime.now();
        List<Likes> l2 = new ArrayList<>();
        l2.add(AllData.likes.get(2));
        l2.add(AllData.likes.get(3));
        List<Comments> c2 = new ArrayList<>();
        c2.add(AllData.comments.get(2));
        c2.add(AllData.comments.get(3));
        Post p2 = new Post(402, "url", date.minusYears(2), "chilling", "adnan1", l2, c2 );

        List<Likes> l3 = new ArrayList<>();
        l3.add(AllData.likes.get(4));
        List< Comments> c3 = new ArrayList<>();
        c3.add(AllData.comments.get(4));
        Post p3 = new Post(403, "url", date.minusYears(1), "New ride", "Ams7", l3, c3 );

        List<Likes> l4 = new ArrayList<>();
        l4.add(AllData.likes.get(5));
        l4.add(AllData.likes.get(6));
        l4.add(AllData.likes.get(7));
        List< Comments> c4 = new ArrayList<>();
        c4.add(AllData.comments.get(5));
        c4.add(AllData.comments.get(6));
        c4.add(AllData.comments.get(7));
        Post p4 = new Post(404, "url", date.minusMonths(10), "Happy New Year", "bob7", l4, c4 );

        List<Likes> l5 = new ArrayList<>();
        l5.add(AllData.likes.get(8));
        l5.add(AllData.likes.get(9));
        List< Comments> c5 = new ArrayList<>();
        c5.add(AllData.comments.get(8));
        c5.add(AllData.comments.get(9));
        Post p5 = new Post(405, "url", date.minusYears(4), "Summer trip", "hotyoga1", l5, c5 );

        Post[] p = {p2, p3, p4, p5};
        List<Post> posts = new ArrayList<>();
        posts.addAll(Arrays.asList(p));
        List<Post> expected = new ArrayList<>();
        expected.add(p4);
        expected.add(p3);

        Assert.assertEquals(postService.getTopKLatestPost(posts, 2), expected);
    }

    @Test
    public void getMostActiveFollowersOfAUser() {
        LocalDateTime date = LocalDateTime.now();

        Likes l1 = new Likes(201, 401, "yousef2", date);
        Likes l2 = new Likes(202, 401, "mama6", date);
        Likes l3 = new Likes(203, 402, "Cole10", date);
        Likes l4 = new Likes(204, 402, "mama6", date);
        Likes l5 = new Likes(205, 403, "smith23", date);
        Likes l6 = new Likes(206, 404, "yousef2", date);
        Likes l7 = new Likes(207, 404, "mama6", date);
        Likes l8 = new Likes(208, 404, "smith23", date);
        Likes l9 = new Likes(209, 405, "bob7", date);
        Likes l10 = new Likes(210, 405, "mike3", date);

        Comments c1 = new Comments(301, 401, "yousef2", "Nice picture", date);
        Comments c2 = new Comments(202, 401, "yousef2", "Nice picture", date);
        Comments c3 = new Comments(203, 402, "Cole10", "Nice picture", date);
        Comments c4 = new Comments(204, 402, "hotyoga1", "Nice picture", date);
        Comments c5 = new Comments(205, 403, "smith23", "Nice picture", date);
        Comments c6 = new Comments(206, 404, "mike3", "Nice picture", date);
        Comments c7 = new Comments(207, 404, "mama6", "Nice picture", date);
        Comments c8 = new Comments(208, 404, "hotyoga1","Nice picture", date);
        Comments c9 = new Comments(209, 405, "bob7", "Nice picture", date);
        Comments c10 = new Comments(210, 405, "mike3","Nice picture", date);

        List<Likes> like2 = new ArrayList<>();
        like2.add(l1);
        like2.add(l2);
        List<Comments> com2 = new ArrayList<>();
        com2.add(c1);
        com2.add(c2);
        Post p2 = new Post(402, "url", date.minusYears(2), "chilling", "adnan1", like2, com2 );

        List<Likes> like3 = new ArrayList<>();
        like3.add(l3);
        List< Comments> com3 = new ArrayList<>();
        com3.add(c3);
        Post p3 = new Post(403, "url", date.minusYears(1), "New ride", "bob7", like3, com3 );

        List<Likes> like4 = new ArrayList<>();
        like4.add(l4);
        like4.add(l5);
        like4.add(l6);
        List< Comments> com4 = new ArrayList<>();
        com4.add(c4);
        com4.add(c5);
        com4.add(c6);
        Post p4 = new Post(404, "url", date.minusMonths(10), "Happy New Year", "adnan1", like4, com4 );

        List<Likes> like5 = new ArrayList<>();
        like5.add(l7);
        like5.add(l8);
        List< Comments> com5 = new ArrayList<>();
        com5.add(c7);
        com5.add(c8);
        Post p5 = new Post(405, "url", date.minusYears(4), "Summer trip", "adnan1", like5, com5 );

        List<Likes> like1 = new ArrayList<>();
        like1.add(l9);
        like1.add(l10);
        List< Comments> com1 = new ArrayList<>();
        com1.add(c9);
        com1.add(c10);
        Post p6 = new Post(411, "url", date, "Balling", "bubacarr89", like1, com1 );

        Post[] p = {p2, p3, p4, p5, p6};
        List<Post> posts = new ArrayList<>();
        posts.addAll(Arrays.asList(p));
        List<String> expected = new ArrayList<>();
        expected.add("mama6");
        expected.add("yousef2");
        expected.add("smith23");

        Assert.assertEquals(postService.getTopKMostActiveFollowersOfAUser(posts, "adnan1", 3), expected);
    }

    @Test
    public void getTopKMostLikedPostInAYear() {

        List<Post> expected = new ArrayList<>();
        expected.add(AllData.post.get(6));
        expected.add(AllData.post.get(7));
        expected.add(AllData.post.get(1));

        Assert.assertEquals(postService.getTopKMostLikedPostInAYear(AllData.post, 2018, 3), expected);
    }

    @Test
    public void getTopKMostCommentedPostInAYear() {
        List<Post> expected = new ArrayList<>();
        expected.add(AllData.post.get(6));
        expected.add(AllData.post.get(7));

        Assert.assertEquals(postService.getTopKMostCommentedPostInAYear(AllData.post, 2018, 2), expected);
    }
}
