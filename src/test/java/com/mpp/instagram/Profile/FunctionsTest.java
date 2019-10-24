package com.mpp.instagram.Profile;

import com.mpp.instagram.FunctionalCode.profile.Profile;
import com.mpp.instagram.FunctionalCode.profile.ProfileFunctions;
import com.mpp.instagram.FunctionalCode.profile.Session;
import com.mpp.instagram.UserFunctionalCode.User.Gender;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FunctionsTest {
     /*
        Oct 21, 2019
        @author: Yousef Quran
        */

    @Test
    public void testMaxAllowedNumberOfFollowings()
    {

        List<Session> veryShortSession = new ArrayList<>();
        veryShortSession.add(new Session(LocalDateTime.now(),LocalDateTime.now().plusMinutes(2)));
        veryShortSession.add(new Session(LocalDateTime.now().plusMinutes(3),LocalDateTime.now().plusMinutes(6)));

        List<Session> shortSession = new ArrayList<>();
        shortSession.add(new Session(LocalDateTime.now(),LocalDateTime.now().plusMinutes(5)));
        shortSession.add(new Session(LocalDateTime.now().plusMinutes(6),LocalDateTime.now().plusMinutes(15)));

        List<Session> longSession = new ArrayList<>();
        longSession.add(new Session(LocalDateTime.now(),LocalDateTime.now().plusMinutes(30)));
        longSession.add(new Session(LocalDateTime.now().plusMinutes(35),LocalDateTime.now().plusMinutes(70)));




        List<Profile> followings=new ArrayList<>(Arrays.asList(
                new Profile("Sibtain","Tarar",23,10, 10, longSession),
                new Profile("Yousef","Khan",50,50, 25, longSession ),
                new Profile("Michael","Jordan",12,30, 30, veryShortSession),
                new Profile("Bubacarr","Ahmed",12,8,8, shortSession),
                new Profile("Wicky","KHan",12,13, 13, shortSession)
        ));

        List<Profile> followers=new ArrayList<>(Arrays.asList(
                new Profile("Adnan","Shehzad",12,15, 15, shortSession),
                new Profile("Sibtain","Tarar",23,10, 10, longSession),
                new Profile("Yousef","Khan",50,50, 25, longSession),
                new Profile("Michael","Jordan",12,30, 30, veryShortSession),
                new Profile("Bubacarr","Ahmed",12,8, 8, shortSession)
        ));


        Assert.assertEquals(Optional.of(3),ProfileFunctions.maxAllowedNumberOfFollowings.apply(followings,followers));

        followings.add(new Profile("newUser","lastName",12,8, 8, longSession));

        Assert.assertEquals(Optional.of(2),ProfileFunctions.maxAllowedNumberOfFollowings.apply(followings,followers));

        followers.add(new Profile("newUser","lastName",12,8, 8, longSession));

        Assert.assertEquals(Optional.of(4),ProfileFunctions.maxAllowedNumberOfFollowings.apply(followings,followers));

        Assert.assertEquals(Optional.of(20),ProfileFunctions.maxAllowedNumberOfFollowings.apply(new ArrayList<Profile>(),followers));

        Assert.assertEquals(Optional.of(Integer.MAX_VALUE),ProfileFunctions.maxAllowedNumberOfFollowings.apply(null,followers));
        Assert.assertEquals(Optional.of(Integer.MAX_VALUE),ProfileFunctions.maxAllowedNumberOfFollowings.apply(followings,null));

        Assert.assertEquals(Optional.of(new ArrayList<Profile>()),ProfileFunctions.getLessActivekFriendsBySessions.apply(followings,followers,0));

        List<Profile> LowActiveUsers = new ArrayList<Profile>();

        LowActiveUsers.add( new Profile("Michael","Jordan",12,30, 30, veryShortSession));

        Assert.assertEquals(Optional.of(LowActiveUsers),ProfileFunctions.getLessActivekFriendsBySessions.apply(followings,followers,1));

        LowActiveUsers.add( new Profile("Bubacarr","Ahmed",12,8, 8, shortSession));
        LowActiveUsers.add( new Profile("Sibtain","Tarar",23,10, 10, longSession));

        Assert.assertEquals(Optional.of(LowActiveUsers),ProfileFunctions.getLessActivekFriendsBySessions.apply(followings,followers,3));

    }



}
