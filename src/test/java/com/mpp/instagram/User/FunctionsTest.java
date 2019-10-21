package com.mpp.instagram.User;

import com.mpp.instagram.FunctionalCode.CUser;
import com.mpp.instagram.FunctionalCode.Gender;
import com.mpp.instagram.FunctionalCode.UserFunctions;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FunctionsTest {

    @Test
    public void testTopMostLikes()
    {
         List<CUser> user=new ArrayList<>(Arrays.asList(
                 new CUser("Adnan","Shehzad",12,15, Gender.MALE,15),
                 new CUser("Sibtain","Tarar",23,10, Gender.MALE,10),
                 new CUser("Yousef","Khan",20,25, Gender.FEMALE,25),
                 new CUser("Michael","Jordan",12,30, Gender.MALE,30),
                 new CUser("Bubacarr","Ahmed",12,8, Gender.FEMALE,8),
                 new CUser("Wicky","KHan",12,13, Gender.MALE,13)
         ));
        List<CUser> output=new ArrayList<CUser>();
        output= UserFunctions.findTopKUsersWithMostLikes.apply(user,Long.valueOf(3));
        output.stream().
                forEach(elem->System.out.println("Element is " + elem.getFname()));
        List<CUser> ExpectedOutput=new ArrayList<>(Arrays.asList(
                new CUser("Michael","Jordan",12,30, Gender.MALE,30),
                new CUser("Yousef","Khan",20,25, Gender.FEMALE,25),
                new CUser("Adnan","Shehzad",12,15, Gender.MALE,15)
                ));
        ExpectedOutput.stream().
                forEach(elem->System.out.println("Element is " + elem.getFname()));
        Assert.assertArrayEquals(new List[]{ExpectedOutput}, new List[]{output});
    }

}
