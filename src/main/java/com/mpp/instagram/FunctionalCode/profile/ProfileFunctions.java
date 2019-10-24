package com.mpp.instagram.FunctionalCode.profile;

import java.time.Duration;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProfileFunctions {

    /*
     * This functions will calculate the friends by finding the intersection between the followings and followers
     * of the user. then it will return the maximum number of profiles that the user can follow.
     * The maximum number is calculated by adding 100% of friends minus the followings number.
     */
    public static customBiFunction<List<Profile>, List<Profile>, Optional<Integer>> maxAllowedNumberOfFollowings = (followings, followers) ->
            Optional.of(
                    (int) (long) (
                            followings == null || followers == null ? Integer.MAX_VALUE :
                                    followings.isEmpty() ? 20 : // by default if you have no friends you can follow up to 20.
                                            ProfileFunctions.getFriends.apply(followings, followers)
                                                    .stream()
                                                    .count() * 2 - followings.size()
                    )


            );

    public static TriFunction<List<Profile>, List<Profile>, Integer, List<Profile>> getLessActivekFriendsBySessions = (followings, followers, k) ->
            Optional.of(
                    followings == null || followers == null || k == null ? new ArrayList<>() :
                            followings.isEmpty() || followers.isEmpty() || k <= 0 ? new ArrayList<Profile>() :
                                    ProfileFunctions.getFriends.apply(followings, followers)
                                            .stream()
                                            .sorted(Comparator.comparingDouble(a -> ProfileFunctions.getSessionsDuration.apply(a.getSessions())))
                                            .limit(k)
                                            .collect(Collectors.toList())
            );


    public static Function<List<Session>, Double> getSessionsDuration = (sessions) ->
            sessions.stream()
            .mapToDouble(d-> Duration.between(d.getStartSession(), d.getEndSession()).toMillis())
                    .sum();



    public static BiFunction<List<Profile>, List<Profile>, List<Profile>> getFriends = (followings, followers) ->
            Stream.concat(
                    followers.stream().filter(followings::contains),
                    followings.stream().filter(followers::contains)
            ).distinct().collect(Collectors.toList());

}
