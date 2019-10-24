package com.mpp.instagram.FunctionalCode.Posts;

import com.mpp.instagram.Models.Post;
import com.mpp.instagram.Models.User;
import org.graalvm.compiler.nodes.calc.IntegerTestNode;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PostService {

    @FunctionalInterface
    public interface TriFunction<A, B, C, R> {
        R apply(A a, B b, C c);
    }

    public Long getNumberOfMutualFriends(List<User> user1, List<User> user2) {
        BiFunction<List<User>, List<User>, Long> mutualFriends = (usr1, usr2) ->
                usr1.stream()
                    .filter(u1 ->
                            usr2.stream()
                            .anyMatch(u2 -> u2.getUsername().equals(u1.getUsername()))
                    ).count();
        return mutualFriends.apply(user1, user2);
    }

    public Float jaccardSimilarities(List<User> ufriends1, List<User> ufriends2) {
        BiFunction<List<User>, List<User>, Float> jaccard = (friends1, friends2) ->
                ((getNumberOfMutualFriends(friends1, friends2))
                        /
                        (float)( friends1.size()+
                                friends2.size()-
                                getNumberOfMutualFriends(friends1,friends2)
                        )
                )*100;
        //Float x = jaccard.apply(ufriends1,ufriends2);
        return jaccard.apply(ufriends1,ufriends2);
    }

    public List<User> getTopKMostLikelyFriendSuggestion(List<User> users, User user, Integer l ) {
        TriFunction<List<User>, User, Integer, List<User>> topKMostLikelyFriendSuggestion =
                (u, target, k) -> users.stream()
                        .filter(p -> !p.getUsername().equals(target.getUsername()))
                        .sorted((u1,u2) -> (int)(jaccardSimilarities(u2.getProfile().getFriends(),
                                target.getProfile().getFriends())
                                -
                                jaccardSimilarities(u1.getProfile().getFriends(),target.getProfile().getFriends())
                        )).limit(k)
                        .collect(Collectors.toList());
        return topKMostLikelyFriendSuggestion.apply(users,user,l);
    }

    public List<Post> getTopKLatestPost(List<Post> posts, Integer l) {
        BiFunction<List<Post>, Integer, List<Post>> topKLatestPost = (p, k) ->
                p.stream()
                    .sorted((p1,p2) -> p2.getUploadDate().compareTo(p1.getUploadDate()))
                    .limit(k)
                    .collect(Collectors.toList());
        return topKLatestPost.apply(posts,l);
    }

    public List<Post> getTopKMostLikedPostInAYear(List<Post> posts, Integer year, Integer l) {
        TriFunction<List<Post>, Integer, Integer, List<Post>> topKMostLikedPostInAYear = (p, yr, k) ->
                p.stream()
                    .filter(e -> e.getUploadDate().getYear() == yr)
                    .sorted((p1,p2) -> p2.getLikes().size() - p1.getLikes().size())
                    .limit(k)
                .collect(Collectors.toList());
        return topKMostLikedPostInAYear.apply(posts, year, l);
    }

    public List<Post> getTopKMostCommentedPostInAYear(List<Post> posts, Integer year, Integer l) {
        TriFunction<List<Post>, Integer, Integer, List<Post>> topKMostCommentedPostInAYear = (p, yr, k) ->
                p.stream()
                        .filter(e -> e.getUploadDate().getYear() == yr)
                        .sorted((p1,p2) -> p2.getComments().size() - p1.getComments().size())
                        .limit(k)
                        .collect(Collectors.toList());
        return topKMostCommentedPostInAYear.apply(posts, year, l);
    }

//    public static List<Integer> getTopKLongestComment(List<User> users, String username, String target) {
//        TriFunction<List<User>, String, String, List<String>> activeUsers = (u , user ,targt) ->
//                u.stream()
//                    .filter(e -> )
//    }

    public List<String> getTopKMostActiveFollowersOfAUser(List<Post> posts, String user, Integer l) {
        TriFunction<List<Post>, String, Integer, List<String>> topKMostActiveFollowersOfAUser = (p, username, k) ->
                p.stream()
                    .filter(e -> e.getUsername().equals(username))
                        .flatMap(x -> Stream.concat(x.getLikes().stream()
                            .map(e -> e.getUsername()), x.getComments().stream().map(s -> s.getUsername())))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet().stream()
                    .sorted((e1, e2) -> (int)(e2.getValue() - e1.getValue()))
                    .limit(k).map(e -> e.getKey()).collect(Collectors.toList());
        return topKMostActiveFollowersOfAUser.apply(posts, user, l);
    }

}
