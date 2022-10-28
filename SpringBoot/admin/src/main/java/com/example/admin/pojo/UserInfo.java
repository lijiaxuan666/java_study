package com.example.admin.pojo;

import java.util.Date;

public class UserInfo {
    private String username;
    private String name;
    private String email;
    private String sex;
    private String phonenumber;
    private Date birthday;
    private String address;
    private Date insert_time;

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSex() {
        return sex;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Date getInsert_time() {
        return insert_time;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setInsert_time(Date insert_time) {
        this.insert_time = insert_time;
    }
}
