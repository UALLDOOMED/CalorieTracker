package com.example.assignmentapp;

import java.util.Date;

public class Userinfo {
    private Integer userid;
    private String name;
    private String surename;
    private String email;
    private Date dob;
    private Integer height;
    private Integer weight;
    private String gender;
    private String address;
    private String postcode;
    private Integer levelOfActivity;
    private Integer stepsPerMile;

    public Userinfo() {

    }

    public Userinfo(Integer userid) {
        this.userid = userid;
    }

    public Userinfo(Integer userid, String name, String surename, String email, Date DOB, Integer height, Integer weight, String gender, String address, String postcode, Integer loa, Integer steps) {
        this.userid = userid;
        this.name = name;
        this.surename = surename;
        this.email = email;
        this.dob = DOB;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.address = address;
        this.postcode = postcode;
        this.levelOfActivity = loa;
        this.stepsPerMile = steps;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Integer getLevelOfActivity() {
        return levelOfActivity;
    }

    public void setLevelOfActivity(Integer levelOfActivity) {
        this.levelOfActivity = levelOfActivity;
    }

    public Integer getStepsPerMile() {
        return stepsPerMile;
    }

    public void setStepsPerMile(Integer stepsPerMile) {
        this.stepsPerMile = stepsPerMile;
    }
}
