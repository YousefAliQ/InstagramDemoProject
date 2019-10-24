package com.mpp.instagram.FunctionalCode;

import com.mpp.instagram.FunctionalCode.profile.ProfileFunctions;
import com.mpp.instagram.UserFunctionalCode.User.CUser;
import com.mpp.instagram.UserFunctionalCode.User.Gender;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class CUsers {//extends Generator<CUser> {

   /* public String fname;
    public String lname;
    public int age;
    public int noofposts;
    public Gender gender;
    public int nooflikes;


    public CUsers() {
        super(CUser.class);
    }

    @Override public CUser generate(
            SourceOfRandomness r,
            GenerationStatus status) {

        return new CUser(
                status.toString(), status.toString(), 12, r.nextInt(), Gender.FEMALE, r.nextInt()
        );
    }*/
}

@RunWith(JUnitQuickcheck.class)
class IdentificationProperties {
   /* @Property(trials = 5)
    private void shouldHold(@From(CUsers.class) List<CUser> followers, @From(CUsers.class) List<CUser> followings) {
        //assertEquals(Optional.of(-1), ProfileFunctions.maxAllowedNumberOfFollowings.apply(followers, followings));

    }*/
}

