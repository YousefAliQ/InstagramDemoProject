package com.mpp.instagram.FunctionalCode;

import com.mpp.instagram.FunctionalCode.profile.ProfileFunctions;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CUser {

    private String fname;
    private String lname;
    private int age;
    private int noofposts;
    private Gender gender;
    private int nooflikes;

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

    public  int getNoofposts() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CUser cUser = (CUser) o;
        return getAge() == cUser.getAge() &&
                getNoofposts() == cUser.getNoofposts() &&
                getNooflikes() == cUser.getNooflikes() &&
                Objects.equals(getFname(), cUser.getFname()) &&
                Objects.equals(getLname(), cUser.getLname()) &&
                getGender() == cUser.getGender();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFname(), getLname(), getAge(), getNoofposts(), getGender(), getNooflikes());
    }

    @Override
    public String toString() {
        return "CUser{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", age=" + age +
                ", noofposts=" + noofposts +
                ", gender=" + gender +
                ", nooflikes=" + nooflikes +
                '}';
    }
}





