package com.example.livecricketscore.model;




public class Details {
    String country1;
    String country2;
    String run1;
    String run2;
    String over1;
    String over2;
    String target;
    String type;
    String result;
    String Image1;
    String Image2;
    int id;

    public Details(String country1, String country2, String run1, String run2, String over1, String over2, String target, String type, String result, String image1, String image2, int id) {
        this.country1 = country1;
        this.country2 = country2;
        this.run1 = run1;
        this.run2 = run2;
        this.over1 = over1;
        this.over2 = over2;
        this.target = target;
        this.type = type;
        this.result = result;
        Image1 = image1;
        Image2 = image2;
        this.id = id;
    }

    public String getCountry1() {
        return country1;
    }

    public void setCountry1(String country1) {
        this.country1 = country1;
    }

    public String getCountry2() {
        return country2;
    }

    public void setCountry2(String country2) {
        this.country2 = country2;
    }

    public String getRun1() {
        return run1;
    }

    public void setRun1(String run1) {
        this.run1 = run1;
    }

    public String getRun2() {
        return run2;
    }

    public void setRun2(String run2) {
        this.run2 = run2;
    }

    public String getOver1() {
        return over1;
    }

    public void setOver1(String over1) {
        this.over1 = over1;
    }

    public String getOver2() {
        return over2;
    }

    public void setOver2(String over2) {
        this.over2 = over2;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getImage1() {
        return Image1;
    }

    public void setImage1(String image1) {
        Image1 = image1;
    }

    public String getImage2() {
        return Image2;
    }

    public void setImage2(String image2) {
        Image2 = image2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
