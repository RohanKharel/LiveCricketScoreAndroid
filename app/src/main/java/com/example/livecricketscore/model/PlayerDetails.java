package com.example.livecricketscore.model;

public class PlayerDetails {

    String name;
    String country;
    String odirun;
    String t20run;
    String testrun;
    String Wickets;

    public PlayerDetails(String name, String country, String odirun, String t20run, String testrun, String wickets) {
        this.name = name;
        this.country = country;
        this.odirun = odirun;
        this.t20run = t20run;
        this.testrun = testrun;
        Wickets = wickets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOdirun() {
        return odirun;
    }

    public void setOdirun(String odirun) {
        this.odirun = odirun;
    }

    public String getT20run() {
        return t20run;
    }

    public void setT20run(String t20run) {
        this.t20run = t20run;
    }

    public String getTestrun() {
        return testrun;
    }

    public void setTestrun(String testrun) {
        this.testrun = testrun;
    }

    public String getWickets() {
        return Wickets;
    }

    public void setWickets(String wickets) {
        Wickets = wickets;
    }
}
