package com.example.assignmentapp;

import java.util.Date;


public class Credential {
    private Integer creid;
    private String username;
    private String passwordHash;
    private Date signUpDate;
    private Userinfo userid;

    public Credential() {
    }

    public Credential(Integer creid) {
        this.creid = creid;
    }

    public Credential(Integer cried, String username, String passwordHash, Date signUpDate) {
        this.creid = cried;
        this.username = username;
        this.passwordHash = passwordHash;
        this.signUpDate = signUpDate;
    }

    public Integer getCreid() {
        return creid;
    }

    public void setCreid(Integer creid) {
        this.creid = creid;
    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    public Userinfo getUserid() {
        return userid;
    }

    public void setUserid(Userinfo userid) {
        this.userid = userid;
    }
}
