package com.mpp.instagram.FunctionalCode.Likes;
import com.mpp.instagram.Models.Post;

import java.util.List;
import java.util.function.BiFunction;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
public class LikeService {

    public List<String> findTheKOldestPosts(List<Post> post, Long l) {
        return getUsernames(post, l, Comparator.comparing(Post::getUploadDate));
    }



    public List<String> findTheTopKLikedPosts(List<Post> post , Long l) {
        return getUsernames(post, l, Comparator.comparing(Post::getLikesCount));
    }

    private List<String> getUsernames(List<Post> post, Long l, Comparator<Post> comparing) {
        BiFunction<List<Post>, Long, List<String>> findTopKLikedPosts=
                (posts, k) -> posts.stream()
                        .sorted(comparing)
                        .map(p -> p.getUsername())
                        .limit(k)
                        .collect(Collectors.toList());
        return findTopKLikedPosts.apply(post, l);
    }

}
