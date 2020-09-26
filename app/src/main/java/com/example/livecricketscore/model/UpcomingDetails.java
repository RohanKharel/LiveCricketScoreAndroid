package com.example.livecricketscore.model;

public class UpcomingDetails {

     String country11;
     String country22;
     String matchdetails1;
     String startdetail;
     int id;

    public UpcomingDetails(String country11, String country22, String matchdetails1, String startdetail, int id) {
        this.country11 = country11;
        this.country22 = country22;
        this.matchdetails1 = matchdetails1;
        this.startdetail = startdetail;
        this.id = id;
    }

    public String getCountry11() {
        return country11;
    }

    public void setCountry11(String country11) {
        this.country11 = country11;
    }

    public String getCountry22() {
        return country22;
    }

    public void setCountry22(String country22) {
        this.country22 = country22;
    }

    public String getMatchdetails1() {
        return matchdetails1;
    }

    public void setMatchdetails1(String matchdetails1) {
        this.matchdetails1 = matchdetails1;
    }

    public String getStartdetail() {
        return startdetail;
    }

    public void setStartdetail(String startdetail) {
        this.startdetail = startdetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
