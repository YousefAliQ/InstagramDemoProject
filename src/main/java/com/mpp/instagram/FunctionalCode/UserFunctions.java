package com.mpp.instagram.FunctionalCode;


import java.util.Comparator;
import java.util.Currency;
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

    public  static BiFunction<List<CUser>,Long,List<CUser>> findTopKUserWithMostPosts=(user,top)->
            user.stream()
                    .sorted(Comparator.comparing(CUser::getNoofposts).reversed())
                    .limit(top)
                    .collect(Collectors.toList());
    public static TriFunction<List<CUser>, CUser, Long, List<String>> findTopKUserswithSameAgeandOppositeGender = (Luser, user, top) ->
            Luser.stream()
                    .filter(gen -> user.getGender() != gen.getGender())
                    .filter(age -> user.getAge() == age.getAge())
                    .limit(top)
                    .map(n -> n.getFname())
                    .collect(Collectors.toList());
}
