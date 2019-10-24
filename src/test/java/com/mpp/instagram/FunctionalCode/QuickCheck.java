package com.mpp.instagram.FunctionalCode;

import com.mpp.instagram.FunctionalCode.CUser;
import com.mpp.instagram.FunctionalCode.Gender;
import com.mpp.instagram.FunctionalCode.profile.ProfileFunctions;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.When;
import com.pholser.junit.quickcheck.generator.Ctor;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Assert;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeThat;


class TestObject {
    /*
    private final String str;

    public TestObject( String str ) {
        this.str = str;
    }
}

interface ContractTest<T> {
    @Property
    public default void testProperty( @From( Ctor.class ) T value ) {
        Assert.assertEquals( null, null );
    }
}*/
/*


@RunWith(JUnitQuickcheck.class)
public class QuickCheck implements ContractTest{


    field.setAccessible(true);

    @Property(trials = 5)
    public void shouldHold(@From(CUsers.class) List<CUser> followers, @From(CUsers.class) List<CUser> followings) {
        assertTrue(Optional.of(-1).equals(ProfileFunctions.maxAllowedNumberOfFollowings.apply(followers, followings)));

    }
*/

    //System.out.println(profileFunctions.maxAllowedNumberOfFollowings(followings,followers));

//        Assert.assertEquals(Optional.of(3),ProfileFunctions.maxAllowedNumberOfFollowings.apply(,followers));
//        Assert.assertEquals(Optional.of(20),ProfileFunctions.maxAllowedNumberOfFollowings.apply(new ArrayList<CUser>(),followers));
//        Assert.assertEquals(Optional.of(-1),ProfileFunctions.maxAllowedNumberOfFollowings.apply(null,followers));



//    @Property(trials = 5)
//    public void assume(List<CUser> followings, List<CUser> followers) {
//
//        assertTrue(Optional.of(-1).equals(ProfileFunctions.maxAllowedNumberOfFollowings.apply(null, null)));
//        //assumeThat(num, greaterThan(0));
//    }
//
//    @Property(trials = 5)
//    public void simple(int num) {
//        System.out.println("simple:" + num);
//        assertTrue(num > 0);
//    }
//
//    @Property(trials = 5)
//    public void inRange(@InRange(minInt = 0, maxInt = 100) int num) {
//        System.out.println("InRange: " + num);
//        assertTrue(num > 0);
//    }
//
//    @Property(trials = 5)
//    public void when(@When(satisfies = "#_ > 1000 && #_ < 100000") int num) {
//        System.out.println("when: " + num);
//        assertTrue(num > 0);
//    }
//
//    @Property(trials = 5)
//    public void seed(@When(seed = 1L) int num) {
//        System.out.println("seed: " + num);
//        assertTrue(num > 0);
//    }

}
