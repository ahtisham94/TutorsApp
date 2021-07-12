package com.example.tutorsapp.models.jobsModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetJobRequestMainResponse {
    @SerializedName("jobsNearBy")
    @Expose
    private ArrayList<GetJobsResponseModel> jobsNearBy;
    @SerializedName("appliedJobs")
    @Expose
    private ArrayList<GetJobsResponseModel> appliedJobs;
    @SerializedName("confirmJobs")
    @Expose
    private ArrayList<GetJobsResponseModel> confirmJobs;

    public ArrayList<GetJobsResponseModel> getJobsNearBy() {
        return jobsNearBy;
    }

    public void setJobsNearBy(ArrayList<GetJobsResponseModel> jobsNearBy) {
        this.jobsNearBy = jobsNearBy;
    }

    public ArrayList<GetJobsResponseModel> getAppliedJobs() {
        return appliedJobs;
    }

    public void setAppliedJobs(ArrayList<GetJobsResponseModel> appliedJobs) {
        this.appliedJobs = appliedJobs;
    }

    public ArrayList<GetJobsResponseModel> getConfirmJobs() {
        return confirmJobs;
    }

    public void setConfirmJobs(ArrayList<GetJobsResponseModel> confirmJobs) {
        this.confirmJobs = confirmJobs;
    }
}
