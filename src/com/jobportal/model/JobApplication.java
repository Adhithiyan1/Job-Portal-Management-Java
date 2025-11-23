package com.jobportal.model;

public class JobApplication {
    private int appId;
    private int jobId;
    private int seekerId;
    private String resumePath;
    private String stage;

    public JobApplication() {}
    
    public JobApplication(int appId, int jobId, int seekerId, String resumePath, String stage) {
        this.appId = appId; 
        this.jobId = jobId; 
        this.seekerId = seekerId; 
        this.resumePath = resumePath; 
        this.stage = stage;
    }
    
    // Getters
    public int getAppId() { return appId; }
    public int getJobId() { return jobId; }
    public int getSeekerId() { return seekerId; }
    public String getResumePath() { return resumePath; }
    public String getStage() { return stage; }
    
    // Setters
    public void setAppId(int appId) { this.appId = appId; }
    public void setJobId(int jobId) { this.jobId = jobId; }
    public void setSeekerId(int seekerId) { this.seekerId = seekerId; }
    public void setResumePath(String resumePath) { this.resumePath = resumePath; }
    public void setStage(String stage) { this.stage = stage; }
}