package com.example.tutorsapp.models;

import java.util.ArrayList;
import java.util.List;

public class RequestedAssignmentModel {
    private String labelText;
    private String date;
    private String status;

    public RequestedAssignmentModel(String labelText, String date, String status) {
        this.labelText = labelText;
        this.date = date;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static List<RequestedAssignmentModel> getModels() {
        List<RequestedAssignmentModel> reqeustedAssignmentModels = new ArrayList<>();
        reqeustedAssignmentModels.add(new RequestedAssignmentModel("Assignment 1", "12/06/2020 12:00pm", "accepted"));
        reqeustedAssignmentModels.add(new RequestedAssignmentModel("Assignment 2", "12/06/2020 12:00pm", "accepted"));
        reqeustedAssignmentModels.add(new RequestedAssignmentModel("Assignment 3", "12/06/2020 12:00pm", "accepted"));
        return reqeustedAssignmentModels;
    }
}
