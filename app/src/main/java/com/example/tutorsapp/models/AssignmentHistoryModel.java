package com.example.tutorsapp.models;

public class AssignmentHistoryModel {

    private int assignmentCode;
    private String date;
    private String parentText;
    private String payment;
    private String remarks;
    private boolean isExpanded;

    public AssignmentHistoryModel(int assignmentCode, String parentText, String date, String payment, String remarks) {
        this.assignmentCode = assignmentCode;
        this.parentText = parentText;
        this.date = date;
        this.payment = payment;
        this.remarks = remarks;
        this.isExpanded = false;
    }

    public AssignmentHistoryModel() {
    }

    public String getParentText() {
        return parentText;
    }

    public void setParentText(String parentText) {
        this.parentText = parentText;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean isExpanded) {
        this.isExpanded = isExpanded;
    }

    public int getAssignmentCode() {
        return assignmentCode;
    }

    public void setAssignmentCode(int assignmentCode) {
        this.assignmentCode = assignmentCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
