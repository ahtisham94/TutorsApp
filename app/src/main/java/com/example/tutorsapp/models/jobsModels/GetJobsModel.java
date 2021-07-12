package com.example.tutorsapp.models.jobsModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetJobsModel {
    @SerializedName("jobDesignation")
    @Expose
    private String jobDesignation;
    @SerializedName("city")
    @Expose
    private int city;
    @SerializedName("instituteName")
    @Expose
    private String instituteName;
    @SerializedName("minSalary")
    @Expose
    private String minSalary;
    @SerializedName("userId")
    @Expose
    private String userId;

    public String getJobDesignation() {
        return jobDesignation;
    }

    public void setJobDesignation(String jobDesignation) {
        this.jobDesignation = jobDesignation;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
