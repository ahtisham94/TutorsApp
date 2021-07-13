package com.example.tutorsapp.models.jobsModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobConfirmationRequestModel {

    @SerializedName("teacherId")
    @Expose
    private int teacherId;

    @SerializedName("jobId")
    @Expose
    private int jobId;

    @SerializedName("availability")
    @Expose
    private boolean availability;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
