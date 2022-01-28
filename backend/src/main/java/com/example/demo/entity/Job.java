package com.example.demo.entity;

public class Job {
    private Integer jobId;

    private String jobName;

    private String jobType;

    private String jobEducation;

    private Integer jobNum;

    private Integer jobSalary;

    private String jobAdress;

    private String jobExperience;

    private String jobWelfare;

    private String companyName;

    private Company company;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobEducation() {
        return jobEducation;
    }

    public void setJobEducation(String jobEducation) {
        this.jobEducation = jobEducation;
    }

    public Integer getJobNum() {
        return jobNum;
    }

    public void setJobNum(Integer jobNum) {
        this.jobNum = jobNum;
    }

    public Integer getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(Integer jobSalary) {
        this.jobSalary = jobSalary;
    }

    public String getJobAdress() {
        return jobAdress;
    }

    public void setJobAdress(String jobAdress) {
        this.jobAdress = jobAdress;
    }

    public String getJobExperience() {
        return jobExperience;
    }

    public void setJobExperience(String jobExperience) {
        this.jobExperience = jobExperience;
    }

    public String getJobWelfare() {
        return jobWelfare;
    }

    public void setJobWelfare(String jobWelfare) {
        this.jobWelfare = jobWelfare;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName() {
        this.companyName = this.company.getCompanyName();
    }
}