package com.example.livecricketscore.model;

public class UserUpdate {

    String fullname;
    String phoneNo;

    public UserUpdate(String fullname, String phoneNo) {
        this.fullname = fullname;
        this.phoneNo = phoneNo;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
