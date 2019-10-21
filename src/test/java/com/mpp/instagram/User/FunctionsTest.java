package com.mpp.instagram.User;

import com.google.common.collect.Iterables;
import com.mpp.instagram.FunctionalCode.CUser;
import com.mpp.instagram.FunctionalCode.Gender;
import com.mpp.instagram.FunctionalCode.UserFunctions;
import org.apache.catalina.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class FunctionsTest {
    //Test Cases Written by Adnan
    List<CUser> user = new ArrayList<>(Arrays.asList(
            new CUser("Adnan", "Shehzad", 25, 15, Gender.MALE, 15),
            new CUser("Sibtain", "Tarar", 25, 10, Gender.MALE, 10),
            new CUser("Yousef", "Khan", 25, 25, Gender.MALE, 25),
            new CUser("Michael", "Jordan", 25, 30, Gender.MALE, 30),
            new CUser("Bubacarr", "Ahmed", 12, 8, Gender.MALE, 8),
            new CUser("sharmeela", "ali", 22, 13, Gender.FEMALE, 13),
            new CUser("Ayesha", "Ahmed", 25, 13, Gender.FEMALE, 13),
        new CUser("Shiwali", "Jain", 25, 13, Gender.FEMALE, 13)
    ));

    @Test
    public void TopMostLikes() {
        List<CUser> output = UserFunctions.findTopKUsersWithMostLikes.apply(user, Long.valueOf(3));
        List<CUser> ExpectedOutput = new ArrayList<>(Arrays.asList(
                new CUser("Michael", "Jordan", 12, 30, Gender.MALE, 30),
                new CUser("Yousef", "Khan", 20, 25, Gender.FEMALE, 25),
                new CUser("Adnan", "Shehzad", 12, 15, Gender.MALE, 15)
        ));
        assertEquals(ExpectedOutput, output);
    }

    @Test
    public void TopMostUsersWithPosts() {
        List<CUser> output= UserFunctions.findTopKUserWithMostPosts.apply(user,Long.valueOf(4));
        List<CUser> ExpectedOutput = new ArrayList<>(Arrays.asList(
                new CUser("Michael", "Jordan", 12, 30, Gender.MALE, 30),
                new CUser("Yousef", "Khan", 20, 25, Gender.FEMALE, 25),
                new CUser("Adnan", "Shehzad", 12, 15, Gender.MALE, 15),
                new CUser("Wicky", "KHan", 12, 13, Gender.MALE, 13)
        ));
        assertEquals(ExpectedOutput,output);
    }
    @Test
    public void DatingUsersSuggestions()
    {
        CUser compareduser=new CUser("Shiwali", "Jain", 25, 13, Gender.FEMALE, 13);
        List<String> ActualOutput= UserFunctions.findTopKUserswithSameAgeandOppositeGender.find(user,compareduser,Long.valueOf(2));
        List<String> ExpectedOutput = new ArrayList<String>(Arrays.asList(
                ("Adnan"),
                ("Sibtain")
        ));
        assertEquals(ExpectedOutput,ActualOutput);
    }
}
