package com.mpp.instagram.FunctionalCode;

import java.util.Objects;

public class CUser {
    private String fname;
    private String lname;
    private int age;
    private int noofposts;
    private Gender gender;
    private int nooflikes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CUser)) return false;
        CUser cUser = (CUser) o;
        return getNoofposts() == cUser.getNoofposts();
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
