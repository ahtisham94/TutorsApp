package com.example.tutorsapp.models;

public class SearchResultTeacherModel {
    private String name;
    private int rating;
    private String distance;
    private String qualification;
    private String fee;
    private String address;
    //private String profileImage;

    public SearchResultTeacherModel(String name, int rating, String distance, String qualification, String fee, String address) {
        this.name = name;
        this.rating = rating;
        this.distance = distance;
        this.qualification = qualification;
        this.fee = fee;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
