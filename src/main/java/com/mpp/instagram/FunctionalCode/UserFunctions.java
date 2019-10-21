package com.mpp.instagram.FunctionalCode;


import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class UserFunctions {

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
}
