package com.dachuyennganh.beershop.model;

public class User {
    private  int userid;
    private String name;
    private String address;
    private int sex;
    private int phone;

    public User() {
    }

    public User(int userid, String name, String address, int sex, int phone) {
        this.userid = userid;
        this.name = name;
        this.address = address;
        this.sex = sex;
        this.phone = phone;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
