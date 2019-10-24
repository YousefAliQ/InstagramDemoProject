package com.mpp.instagram.UserFunctionalCode.User;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class UserFunctions {
    //Code By Adnan//
    public static BiFunction<List<CUser>, Long, List<CUser>> findTopKUsersWithMostLikes =
            (users, k) -> users.stream()
                    .sorted(Comparator.comparing(CUser::getNooflikes).reversed())
                    .limit(k)
                    .collect(Collectors.toList());
    //Code By Adnan
    public  static BiFunction<List<CUser>,Long,List<CUser>> findTopKUserWithMostPosts=(user,top)->
            user.stream()
                    .sorted(Comparator.comparing(CUser::getNoofposts).reversed())
                    .limit(top)
                    .collect(Collectors.toList());
    //Code By Adnan Shehzad
    public static TriFunction<List<CUser>, CUser, Long, List<String>> findTopKUserswithSameAgeandOppositeGender = (Luser, user, top) ->
            Luser.stream()
                    .filter(gen -> user.getGender() != gen.getGender())
                    .filter(age -> user.getAge() == age.getAge())
                    .limit(top)
                    .map(n -> n.getFname())
                    .collect(Collectors.toList());
   //Code By Adnan Shehzad
    //Function For Returning the Time Difference between two dates
    public static BiFunction<LocalDateTime,LocalDateTime,Long>calcdifferenceinSeconds=(signin,signout)->
    {
        return ChronoUnit.SECONDS.between(signin,signout);
    };
    public static BiFunction<List<CUser>,Long,List<CUser>> TopMostActiveUsersByTimeDifference=(list,top)->
            list.stream()
                    .filter(in->in.getSession())
                    .map(in->new HashMap<CUser,Long>() {{
                        put(in,calcdifferenceinSeconds.apply(in.getSignin(),in.getLogout()));
                    }}.entrySet().stream().findFirst().get())
                    //.sorted(Comparator.comparing(HashMap.Entry::getValue)) //How to reverse this
                    .sorted((e1,e2)-> Math.toIntExact(Long.valueOf(e2.getValue() - e1.getValue())))
                    .limit(top)
                    .map(in->in.getKey())
                .collect(Collectors.toList());
}
