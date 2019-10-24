package com.mpp.instagram.Likes;

import com.mpp.instagram.Data.AllData;
import com.mpp.instagram.FunctionalCode.Likes.LikeService;
import com.mpp.instagram.Models.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LikeServiceTest {

    private LikeService likeService = new LikeService();

    @Before
    public void init(){
        likeService = new LikeService();
        AllData d = new AllData();
        d.fillData();
    }

    @Test
    public void topKOldestPostsTest() {

        List<Post> post = AllData.post;

        List<String> output=new ArrayList<>();
        output= likeService.findTheKOldestPosts(post, 3L);
        List<String> PostUsernames = new ArrayList<String>();

        List<String> ExpectedOutputUsernames=new ArrayList<String>();
        ExpectedOutputUsernames.add("adnan1");
        ExpectedOutputUsernames.add("max5");
        ExpectedOutputUsernames.add("yeast2");


        Assert.assertEquals(output, ExpectedOutputUsernames);
    }



    @Test
    public void topKLikedPostsTest() {

        List<Post> post = AllData.post;
        List<String> output=new ArrayList<>();
        output= likeService.findTheTopKLikedPosts(post, 3L);
        List<String> PostUsernames = new ArrayList<String>();

        List<String> ExpectedOutputUsernames=new ArrayList<String>();
        ExpectedOutputUsernames.add("Ams7");
        ExpectedOutputUsernames.add("max5");
        ExpectedOutputUsernames.add("bubacarr89");


        Assert.assertEquals(output, ExpectedOutputUsernames);


    }




}
