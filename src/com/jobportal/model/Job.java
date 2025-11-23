package com.jobportal.model;

public class Job {
    private int jobId;
    private int employerId;
    private String title;
    private String jd;
    private String pay;
    private String type; // REMOTE/ONSITE/HYBRID

    public Job() {}
    
    public Job(int jobId, int employerId, String title, String jd, String pay, String type) {
        this.jobId = jobId; 
        this.employerId = employerId; 
        this.title = title; 
        this.jd = jd; 
        this.pay = pay; 
        this.type = type;
    }
    
    // Getters
    public int getJobId() { return jobId; }
    public int getEmployerId() { return employerId; }
    public String getTitle() { return title; }
    public String getJd() { return jd; }
    public String getPay() { return pay; }
    public String getType() { return type; }
    
    // Setters (optional, but good to have)
    public void setJobId(int jobId) { this.jobId = jobId; }
    public void setEmployerId(int employerId) { this.employerId = employerId; }
    public void setTitle(String title) { this.title = title; }
    public void setJd(String jd) { this.jd = jd; }
    public void setPay(String pay) { this.pay = pay; }
    public void setType(String type) { this.type = type; }
}