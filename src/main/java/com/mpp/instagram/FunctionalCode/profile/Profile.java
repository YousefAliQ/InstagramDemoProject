package com.mpp.instagram.FunctionalCode.profile;

import com.mpp.instagram.UserFunctionalCode.User.Gender;

import java.util.List;
import java.util.Objects;

public class Profile<Private> {

    private String fname;
    private String lname;
    private int age;
    private int noOfPosts;
    private int noOfLikes;
    private List<Session> sessions;

    public Profile(String fname, String lname, int age, int noOfPosts,  int noOfLikes, List<Session> sessions) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.noOfPosts = noOfPosts;

        this.noOfLikes = noOfLikes;
        this.sessions = sessions;
    }

    int getNoOfPosts() {
        return noOfPosts;
    }

     void setNoOfPosts(int noOfPosts) {
        this.noOfPosts = noOfPosts;
    }

     int getAge() {
        return age;
    }

     void setAge(int age) {
        this.age = age;
    }

     String getLname() {
        return lname;
    }

     void setLname(String lname) {
        this.lname = lname;
    }

     String getFname() {
        return fname;
    }

     void setFname(String fname) {
        this.fname = fname;
    }

    int getNoOfLikes() {
        return noOfLikes;
    }

     void setNoOfLikes(int noOfLikes) {
        this.noOfLikes = noOfLikes;
    }

    List<Session> getSessions() {
        return sessions;
    }

     void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile cUser = (Profile) o;
        return getAge() == cUser.getAge() &&
                getNoOfPosts() == cUser.getNoOfPosts() &&
                getNoOfLikes() == cUser.getNoOfLikes() &&
                Objects.equals(getFname(), cUser.getFname()) &&
                Objects.equals(getLname(), cUser.getLname());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getFname(), getLname(), getAge(), getNoOfPosts(), getNoOfLikes());
    }

    @Override
    public String toString() {
        return "Profile{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", age=" + age +
                ", noOfPosts=" + noOfPosts +
                ", noOfLikes=" + noOfLikes +
                ", sessions=" + sessions +
                '}';
    }
}





