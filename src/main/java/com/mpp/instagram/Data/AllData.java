package com.mpp.instagram.Data;

import com.mpp.instagram.Models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AllData {
    public static List<Post> post;
    public static List<User> users;
    public static List<Likes> likes;
    public static List<Comments> comments;
    public static List<Profile> profiles;

    public void fillData() {
        fillLikes();
        fillComments();
        fillUsers();
        fillProfiles();
        fillProfilesInUsers();
        fillPosts();
    }
    public void fillUsers() {
        Profile profile = null;

        users = new ArrayList<>();
        User u1 = new User(101, "yousef2", "Yousef Ali", "yousef@mail.com", "AliYu123", profile);
        User u2 = new User(102, "Mike3", "Micheal Young", "mike@mail.com", "MikeYu23", profile);
        User u3 = new User(103, "bob7", "bobson bob", "bob@mail.com", "bobson8", profile);
        User u4 = new User(104, "yeast2", "Yeast beast", "yeast@mail.com", "yebeast3", profile);
        User u5 = new User(105, "micheal5", "Micheal pena", "micheal@mail.com", "mpena12", profile);
        User u6 = new User(106, "adnan1", "Adnan Shazad", "adnan@mail.com", "Shadnan13", profile);
        User u7 = new User(107, "john3", "John Lee", "lee@mail.com", "click123", profile);
        User u8 = new User(108, "max5", "Max Tom", "max@mail.com", "best183", profile);
        User u9 = new User(109, "bubacarr9", "Bubacarr Jallow", "buba@mail.com", "buba89", profile);
        User u10 = new User(110, "Ams7", "Ams Strong", "ams@mail.com", "strong12", profile);
        User u11 = new User(111, "stevens1", "Steven Seagal", "steven@mail.com", "Seven7", profile);
        User u12 = new User(112, "smith23", "Sam Smith", "sam@mail.com", "ssmith45", profile);
        User u13 = new User(113, "hotyoga1", "Hot Yoga", "yoga@mail.com", "yogiyo23",profile);
        User u14 = new User(114, "mama6", "Mamacita", "mama@mail.com", "senorita5", profile);
        User u15 = new User(115, "cole10", "john Cole", "cole@mail.com", "van23", profile);

        User[] u = { u1, u2, u3, u4, u5, u6, u7, u8, u9, u10 ,u11, u12, u13, u14, u15 };
        users.addAll(Arrays.asList(u));
    }
    public void fillProfilesInUsers() {
        users.get(0).setProfile(profiles.get(0));
        users.get(1).setProfile(profiles.get(1));
        users.get(2).setProfile(profiles.get(2));
        users.get(3).setProfile(profiles.get(3));
        users.get(4).setProfile(profiles.get(4));
        users.get(5).setProfile(profiles.get(5));
        users.get(6).setProfile(profiles.get(6));
        users.get(7).setProfile(profiles.get(7));
        users.get(8).setProfile(profiles.get(8));
        users.get(9).setProfile(profiles.get(9));
        users.get(10).setProfile(profiles.get(10));
        users.get(11).setProfile(profiles.get(11));
        users.get(12).setProfile(profiles.get(12));
        users.get(13).setProfile(profiles.get(13));
        users.get(14).setProfile(profiles.get(14));
    }

    public void fillProfiles() {
        profiles = new ArrayList<>();
        List<User> u1 = new ArrayList<>();
        u1.add(users.get(1));
        u1.add(users.get(2));
        u1.add(users.get(3));
        Profile p1 = new Profile(001, "yousef2", 2, 3, 4, "Business", u1);

        List<User> u2 = new ArrayList<>();
        u2.add(users.get(0));
        u2.add(users.get(2));
        u2.add(users.get(3));
        Profile p2 = new Profile(002, "Mike3", 1, 3, 2, "Model", u2);

        List<User> u3 = new ArrayList<>();
        u1.add(users.get(0));
        u1.add(users.get(1));
        Profile p3 = new Profile(003, "bob7", 2, 2, 3, "Dance", u3);

        List<User> u4 = new ArrayList<>();
        Profile p4 = new Profile(004, "yeast2", 1, 0, 2, "Singing", u4);

        List<User> u5 = new ArrayList<>();
        u5.add(users.get(0));
        u5.add(users.get(1));
        u5.add(users.get(2));
        u5.add(users.get(3));
        Profile p5 = new Profile(005, "adnan1", 5, 4, 1, "Enterpreneur", u5);

        List<User> u6 = new ArrayList<>();
        u6.add(users.get(7));
        u6.add(users.get(5));
        Profile p6 = new Profile(006, "micheal5", 10, 2, 6, "Actor", u6);

        List<User> u7 = new ArrayList<>();
        u7.add(users.get(6));
        u7.add(users.get(7));
        u7.add(users.get(9));
        Profile p7 = new Profile(007, "john3", 20, 3, 3, "", u7);

        List<User> u8 = new ArrayList<>();
        u8.add(users.get(10));
        u8.add(users.get(14));
        Profile p8 = new Profile(016, "max5", 6, 2, 8, "Architect", u8);

        List<User> u9 = new ArrayList<>();
        u9.add(users.get(6));
        u9.add(users.get(9));
        u9.add(users.get(12));
        u9.add(users.get(14));
        Profile p9 = new Profile(017, "bubacarr89", 8, 4, 9, "Scientist", u9);

        List<User> u10 = new ArrayList<>();
        u10.add(users.get(13));
        Profile p10 = new Profile(010, "Ams7", 3, 1, 12, "journalists", u10);

        List<User> u11 = new ArrayList<>();
        u11.add(users.get(10));
        u11.add(users.get(11));
        Profile p11 = new Profile(011, "stevens1", 10, 2, 5, "Smooth", u11);

        List<User> u12 = new ArrayList<>();
        u12.add(users.get(8));
        u12.add(users.get(9));
        u12.add(users.get(5));
        Profile p12 = new Profile(012, "smith23", 4, 3, 7, "Actress", u12);

        List<User> u13 = new ArrayList<>();
        u13.add(users.get(10));
        u13.add(users.get(11));
        u13.add(users.get(8));
        Profile p13 = new Profile(013, "hotyoga1", 15, 3, 9, "DJ", u13);

        List<User> u14 = new ArrayList<>();
        u14.add(users.get(7));
        u14.add(users.get(5));
        u14.add(users.get(14));
        u14.add(users.get(13));
        Profile p14 = new Profile(014, "mama6", 6, 4, 12, "Ecool", u14);

        List<User> u15 = new ArrayList<>();
        u15.add(users.get(12));
        u15.add(users.get(9));
        Profile p15 = new Profile(015, "Cole10", 3, 2, 10, "Dancing", u15);

        Profile[] p = {p1, p2, p3, p4, p5 ,p6, p7, p8, p9, p10, p11, p12, p13, p14, p15};
        profiles.addAll(Arrays.asList(p));
    }

    public void fillLikes() {
        likes = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();
        Likes l1 = new Likes(201, 401, "yousef2", date);
        Likes l2 = new Likes(202, 401, "Ams7", date);
        Likes l3 = new Likes(203, 402, "Cole10", date);
        Likes l4 = new Likes(204, 402, "mama6", date);
        Likes l5 = new Likes(205, 403, "smith23", date);
        Likes l6 = new Likes(206, 404, "yousef2", date);
        Likes l7 = new Likes(207, 404, "mama6", date);
        Likes l8 = new Likes(208, 404, "hotyoga1", date);
        Likes l9 = new Likes(209, 405, "bob7", date);
        Likes l10 = new Likes(210, 405, "mike3", date);
        Likes l11 = new Likes(211, 406, "mike3", date);
        Likes l12 = new Likes(212, 408, "bubacarr89", date);
        Likes l13 = new Likes(213, 407, "Ams7", date);
        Likes l14 = new Likes(214, 408, "max5", date);
        Likes l15 = new Likes(215, 407, "Cole10", date);
        Likes l16 = new Likes(216, 409, "bob7", date);
        Likes l17 = new Likes(217, 409, "hotyoga1", date);
        Likes l18 = new Likes(218, 410, "yeast2", date);
        Likes l19 = new Likes(219, 410, "adnan1", date);
        Likes l20 = new Likes(220, 407, "adnan1", date);
        Likes l21 = new Likes(221, 411, "max5", date);
        Likes l22 = new Likes(222, 412, "smith23", date);
        Likes l23 = new Likes(223, 408, "stevens1", date);

        Likes[] l = {l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20, l21, l22, l23};
        likes.addAll(Arrays.asList(l));
    }

    public void fillComments() {
        comments = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();
        Comments c1 = new Comments(301, 401, "yousef2", "Nice picture", date);
        Comments c2 = new Comments(202, 401, "Ams7", "Nice picture", date);
        Comments c3 = new Comments(203, 402, "Cole10", "Nice picture", date);
        Comments c4 = new Comments(204, 402, "mama6", "Nice picture", date);
        Comments c5 = new Comments(205, 403, "smith23", "Nice picture", date);
        Comments c6 = new Comments(206, 404, "yousef2", "Nice picture", date);
        Comments c7 = new Comments(207, 404, "mama6", "Nice picture", date);
        Comments c8 = new Comments(208, 404, "hotyoga1","Nice picture", date);
        Comments c9 = new Comments(209, 405, "bob7", "Nice picture", date);
        Comments c10 = new Comments(210, 405, "mike3","Nice picture", date);
        Comments c11 = new Comments(211, 406, "mike3", "Nice picture", date);
        Comments c12 = new Comments(212, 408, "bubacarr89", "Nice picture", date);
        Comments c13 = new Comments(213, 407, "Ams7", "Nice picture", date);
        Comments c14 = new Comments(214, 408, "max5", "Nice picture", date);
        Comments c15 = new Comments(215, 407, "Cole10", "Nice picture", date);
        Comments c16 = new Comments(216, 409, "bob7", "Nice picture", date);
        Comments c17 = new Comments(217, 409, "hotyoga1","Nice picture", date);
        Comments c18 = new Comments(218, 410, "yeast2", "Nice picture", date);
        Comments c19 = new Comments(219, 410, "adnan1", "Nice picture", date);
        Comments c20 = new Comments(220, 407, "adnan1", "Nice picture", date);
        Comments c21 = new Comments(221, 411, "max5", "Nice picture", date);
        Comments c22 = new Comments(222, 412, "smith23", "Nice picture", date);
        Comments c23 = new Comments(223, 408, "stevens1", "Nice picture", date);

        Comments[] c = {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23};
        comments.addAll(Arrays.asList(c));
    }

    public void fillPosts() {
        post = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();

        List<Likes> l1 = new ArrayList<>();
        l1.add(likes.get(0));
        l1.add(likes.get(1));
        List< Comments> c1 = new ArrayList<>();
        c1.add(comments.get(0));
        c1.add(comments.get(1));
        Post p1 = new Post(401, "url", date, "congrats", "adnan1", l1, c1 );

        List<Likes> l2 = new ArrayList<>();
        l2.add(likes.get(2));
        l2.add(likes.get(3));
        List< Comments> c2 = new ArrayList<>();
        c2.add(comments.get(2));
        c2.add(comments.get(3));
        Post p2 = new Post(402, "url", date.minusYears(1), "chilling", "adnan1", l2, c2 );

        List<Likes> l3 = new ArrayList<>();
        l3.add(likes.get(4));
        List< Comments> c3 = new ArrayList<>();
        c3.add(comments.get(4));
        Post p3 = new Post(403, "url", date, "New ride", "Ams7", l3, c3 );

        List<Likes> l4 = new ArrayList<>();
        l4.add(likes.get(5));
        l4.add(likes.get(6));
        l4.add(likes.get(7));
        List< Comments> c4 = new ArrayList<>();
        c4.add(comments.get(5));
        c4.add(comments.get(6));
        c4.add(comments.get(7));
        Post p4 = new Post(404, "url", date, "Happy New Year", "bob7", l4, c4 );

        List<Likes> l5 = new ArrayList<>();
        l5.add(likes.get(8));
        l5.add(likes.get(9));
        List< Comments> c5 = new ArrayList<>();
        c5.add(comments.get(8));
        c5.add(comments.get(9));
        Post p5 = new Post(405, "url", date, "Summer trip", "hotyoga1", l5, c5 );

        List<Likes> l6 = new ArrayList<>();
        l6.add(likes.get(10));
        List< Comments> c6 = new ArrayList<>();
        c6.add(comments.get(10));
        Post p6 = new Post(406, "url", date.minusYears(1), "Women's right", "max5", l6, c6 );

        List<Likes> l7 = new ArrayList<>();
        l7.add(likes.get(12));
        l7.add(likes.get(14));
        l7.add(likes.get(19));
        List< Comments> c7 = new ArrayList<>();
        c7.add(comments.get(12));
        c7.add(comments.get(14));
        c7.add(comments.get(19));
        Post p7 = new Post(407, "url", date.minusYears(1), "Christmas Time", "yeast2", l7, c7 );

        List<Likes> l8 = new ArrayList<>();
        l8.add(likes.get(11));
        l8.add(likes.get(13));
        l8.add(likes.get(22));
        List< Comments> c8 = new ArrayList<>();
        c8.add(comments.get(11));
        c8.add(comments.get(13));
        c8.add(comments.get(13));
        Post p8 = new Post(408, "url", date.minusYears(1), "Dope", "smith23", l8, c8 );

        List<Likes> l9 = new ArrayList<>();
        l9.add(likes.get(15));
        l9.add(likes.get(16));
        List<Comments> c9 = new ArrayList<>();
        c9.add(comments.get(15));
        c9.add(comments.get(16));
        Post p9 = new Post(409, "url", date.minusYears(1), "Catchy", "Cole10", l9, c9 );

        List<Likes> l10 = new ArrayList<>();
        l10.add(likes.get(17));
        l10.add(likes.get(18));
        List< Comments> c10 = new ArrayList<>();
        c10.add(comments.get(17));
        c10.add(comments.get(18));
        Post p10 = new Post(410, "url", date, "Enjoying", "yousef2", l10, c10 );

        List<Likes> l11 = new ArrayList<>();
        l11.add(likes.get(20));
        List< Comments> c11 = new ArrayList<>();
        c11.add(comments.get(20));
        Post p11 = new Post(411, "url", date.minusYears(1), "Balling", "bubacarr89", l11, c11 );

        List<Likes> l12 = new ArrayList<>();
        l12.add(likes.get(21));
        List< Comments> c12 = new ArrayList<>();
        c12.add(comments.get(21));
        Post p12 = new Post(412, "url", date, "Sports", "mama6", l12, c12 );

        Post[] p = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12};
        post.addAll(Arrays.asList(p));
    }
}
