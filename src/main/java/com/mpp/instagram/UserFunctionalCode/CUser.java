package com.mpp.instagram.UserFunctionalCode;

import java.time.LocalDateTime;

public class CUser {

    private String username;
    private String email;
    private String fname;
    private String lname;
    private int age;
    private int noofposts;
    private Gender gender;
    private int nooflikes;
    private LocalDateTime signin;
    private LocalDateTime logout;
    private boolean session;

    public boolean getSession() {
        return session;
    }

    public void setSession(boolean session) {
        this.session = session;
    }

    public LocalDateTime getSignin() {
        return signin;
    }

    public void setSignin(LocalDateTime signin) {
        this.signin = signin;
    }

    public LocalDateTime getLogout() {
        return logout;
    }

    public void setLogout(LocalDateTime logout) {
        this.logout = logout;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CUser)) return false;
        CUser cUser = (CUser) o;
        return getNoofposts() == cUser.getNoofposts();
    }

    public CUser(String fname, String lname, int age, int noofposts, Gender gender, int nooflikes, LocalDateTime signin, LocalDateTime logout, boolean session) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.noofposts = noofposts;
        this.gender = gender;
        this.nooflikes = nooflikes;
        this.signin = signin;
        this.logout = logout;
        this.session = session;
    }

    public CUser(String fname, String lname, int age, int noofposts, Gender gender, int nooflikes) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.noofposts = noofposts;
        this.gender = gender;
        this.nooflikes = nooflikes;
    }
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getNoofposts() {
        return noofposts;
    }

    public void setNoofposts(int noofposts) {
        this.noofposts = noofposts;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getNooflikes() {
        return nooflikes;
    }

    public void setNooflikes(int nooflikes) {
        this.nooflikes = nooflikes;
    }
}
