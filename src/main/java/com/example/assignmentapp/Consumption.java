package com.example.assignmentapp;

import java.util.Date;

public class Consumption {
    private Integer conid;
    private Date date;
    private int quantity;
    private Food foodid;
    private Userinfo userid;
    public Consumption(){
    }

    public Consumption(Integer conid) {
        this.conid = conid;
    }

    public Consumption(Integer conid, Date date, int quantity) {
        this.conid = conid;
        this.date = date;
        this.quantity = quantity;
    }

    public Integer getConid() {
        return conid;
    }

    public void setConid(Integer conid) {
        this.conid = conid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Food getFoodid() {
        return foodid;
    }

    public void setFoodid(Food foodid) {
        this.foodid = foodid;
    }

    public Userinfo getUserid() {
        return userid;
    }

    public void setUserid(Userinfo userid) {
        this.userid = userid;
    }
}
