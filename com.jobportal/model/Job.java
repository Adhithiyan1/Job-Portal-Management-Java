package com.jobportal.model;

public class Job {
    private int jobId;
    private int employerId;
    private String title;
    private String jd;
    private String pay;
    private String type;

    public Job() {}
    public Job(int jobId, int employerId, String title, String jd, String pay, String type) 
    {
        this.jobId = jobId; 
        this.employerId = employerId; 
        this.title = title; 
        this.jd = jd; 
        this.pay = pay; 
        this.type = type;
    }
    public int getJobId() { return jobId; }
    public String getTitle() { return title; }
    public String getType() { return type; }
}
