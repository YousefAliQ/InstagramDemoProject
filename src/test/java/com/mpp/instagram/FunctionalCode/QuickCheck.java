package com.mpp.instagram.FunctionalCode;

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
