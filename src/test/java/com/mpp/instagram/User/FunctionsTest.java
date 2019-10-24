package com.mpp.instagram.User;

import com.mpp.instagram.UserFunctionalCode.User.CUser;
import com.mpp.instagram.UserFunctionalCode.User.Gender;
import com.mpp.instagram.UserFunctionalCode.User.UserFunctions;
import org.junit.Test;

import java.time.LocalDateTime;
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
    //DateTime Initialization
    private LocalDateTime signin1 =
            LocalDateTime.of(2019, 11, 15, 14, 30, 10, 123456789);
    private LocalDateTime signout1 =
            LocalDateTime.of(2019, 11, 15, 15, 30, 10, 123456789);
    private LocalDateTime signin2 =
            LocalDateTime.of(2019, 11, 16, 10, 30, 10, 123456789);
    private LocalDateTime signout2 =
            LocalDateTime.of(2019, 11, 16, 12, 30, 10, 123456789);
    private LocalDateTime signin3 =
            LocalDateTime.of(2019, 11, 17, 9, 30, 10, 123456789);
    private LocalDateTime signout3 =
            LocalDateTime.of(2019, 11, 17, 12, 30, 10, 123456789);
    private LocalDateTime signin4 =
            LocalDateTime.of(2019, 11, 18, 17, 30, 10, 123456789);
    private LocalDateTime signout4 =
            LocalDateTime.of(2019, 11, 18, 22, 30, 10, 123456789);
    //Second List for Checking Users
    List<CUser> user1 = new ArrayList<>(Arrays.asList(
            new CUser("Sibtain", "Tarar", 25, 10, Gender.MALE, 10,signin2,signout2,true),
            new CUser("Adnan", "Shehzad", 25, 15, Gender.MALE, 15,signin1,signout1,true),
            new CUser("Michael", "Jordan", 25, 30, Gender.MALE, 30,signin4,signout4,false),
            new CUser("Yousef", "Khan", 25, 25, Gender.MALE, 25,signin3,signout3,true)
    ));
    @Test
    public void TopMostLikes() {
        List<CUser> output = UserFunctions.findTopKUsersWithMostLikes.apply(user, 3L);
        List<CUser> ExpectedOutput = new ArrayList<>(Arrays.asList(
                new CUser("Michael", "Jordan", 12, 30, Gender.MALE, 30),
                new CUser("Yousef", "Khan", 20, 25, Gender.FEMALE, 25),
                new CUser("Adnan", "Shehzad", 12, 15, Gender.MALE, 15)
        ));
        assertEquals(ExpectedOutput, output);
    }
    @Test
    public void TopMostUsersWithPosts() {
        List<CUser> output= UserFunctions.findTopKUserWithMostPosts.apply(user, 4L);
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
        List<String> ActualOutput= UserFunctions.findTopKUserswithSameAgeandOppositeGender.apply(user,compareduser,Long.valueOf(2));
        List<String> ExpectedOutput = new ArrayList<String>(Arrays.asList(
                ("Adnan"),
                ("Sibtain")
        ));
        assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void TestDifferenceinSeconds()
    {
        Long actualoutput= UserFunctions.calcdifferenceinSeconds.apply(signin1,signout1);
        System.out.println("Timimg Difference is " + actualoutput);
        Long expected= 3600L;
        assertEquals(expected,actualoutput);
    }
    @Test
    public void TestMostActiveUsersperTimeDifferenceandSessions()
    {
        List<CUser> actualresult=UserFunctions.TopMostActiveUsersByTimeDifference.apply(user1,2L);
        List<CUser> expectedOutput=new ArrayList<>(Arrays.asList(
                new CUser("Yousef", "Khan", 25, 25, Gender.MALE, 25,signin3,signout3,true),
                new CUser("Sibtain", "Tarar", 25, 10, Gender.MALE, 10,signin2,signout2,true)
               ));
        assertEquals(expectedOutput,actualresult);
    }
}
