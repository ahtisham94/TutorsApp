package com.example.tutorsapp.models.jobsModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetJobsResponseModel implements Serializable {
    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("instituteName")
    @Expose
    private String instituteName;
    @SerializedName("functionalArea")
    @Expose
    private String functionalArea;
    @SerializedName("ownerId")
    @Expose
    private Integer ownerId;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("qulification")
    @Expose
    private String qulification;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("experience")
    @Expose
    private String experience;
    @SerializedName("jobDescription")
    @Expose
    private String jobDescription;
    @SerializedName("jobArea")
    @Expose
    private String jobArea;
    @SerializedName("jobCity")
    @Expose
    private Integer jobCity;
    @SerializedName("jobCityName")
    @Expose
    private String jobCityName;
    @SerializedName("jobVacancy")
    @Expose
    private String jobVacancy;
    @SerializedName("minSalary")
    @Expose
    private String minSalary;
    @SerializedName("maxSalary")
    @Expose
    private String maxSalary;
    @SerializedName("salaryOption")
    @Expose
    private Object salaryOption;
    @SerializedName("applyBefore")
    @Expose
    private String applyBefore;
    @SerializedName("postJobDate")
    @Expose
    private String postJobDate;
    @SerializedName("jobStatus")
    @Expose
    private Integer jobStatus;
    @SerializedName("jobStatusName")
    @Expose
    private String jobStatusName;
    @SerializedName("isDeleted")
    @Expose
    private Object isDeleted;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("createdBy")
    @Expose
    private Object createdBy;
    @SerializedName("updated")
    @Expose
    private Object updated;
    @SerializedName("updatedBy")
    @Expose
    private Object updatedBy;
    @SerializedName("ended")
    @Expose
    private Object ended;
    @SerializedName("endedBy")
    @Expose
    private Object endedBy;
    @SerializedName("jobPosts")
    @Expose
    private String jobPosts;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("finalSelectionDate")
    @Expose
    private String finalSelectionDate;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getFunctionalArea() {
        return functionalArea;
    }

    public void setFunctionalArea(String functionalArea) {
        this.functionalArea = functionalArea;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getQulification() {
        return qulification;
    }

    public void setQulification(String qulification) {
        this.qulification = qulification;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobArea() {
        return jobArea;
    }

    public void setJobArea(String jobArea) {
        this.jobArea = jobArea;
    }

    public Integer getJobCity() {
        return jobCity;
    }

    public void setJobCity(Integer jobCity) {
        this.jobCity = jobCity;
    }

    public String getJobCityName() {
        return jobCityName;
    }

    public void setJobCityName(String jobCityName) {
        this.jobCityName = jobCityName;
    }

    public String getJobVacancy() {
        return jobVacancy;
    }

    public void setJobVacancy(String jobVacancy) {
        this.jobVacancy = jobVacancy;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Object getSalaryOption() {
        return salaryOption;
    }

    public void setSalaryOption(Object salaryOption) {
        this.salaryOption = salaryOption;
    }

    public String getApplyBefore() {
        return applyBefore;
    }

    public void setApplyBefore(String applyBefore) {
        this.applyBefore = applyBefore;
    }

    public String getPostJobDate() {
        return postJobDate;
    }

    public void setPostJobDate(String postJobDate) {
        this.postJobDate = postJobDate;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobStatusName() {
        return jobStatusName;
    }

    public void setJobStatusName(String jobStatusName) {
        this.jobStatusName = jobStatusName;
    }

    public Object getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Object isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public Object getUpdated() {
        return updated;
    }

    public void setUpdated(Object updated) {
        this.updated = updated;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Object getEnded() {
        return ended;
    }

    public void setEnded(Object ended) {
        this.ended = ended;
    }

    public Object getEndedBy() {
        return endedBy;
    }

    public void setEndedBy(Object endedBy) {
        this.endedBy = endedBy;
    }

    public String getJobPosts() {
        return jobPosts;
    }

    public void setJobPosts(String jobPosts) {
        this.jobPosts = jobPosts;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFinalSelectionDate() {
        return finalSelectionDate;
    }

    public void setFinalSelectionDate(String finalSelectionDate) {
        this.finalSelectionDate = finalSelectionDate;
    }
}
