package com.jobportal.model;

public class JobApplication {
    private int appId;
    private int jobId;
    private int seekerId;
    private String resumePath;
    private String stage;

    public JobApplication() {}
    public JobApplication(int appId, int jobId, int seekerId, String resumePath, String stage) 
    {
        this.appId = appId; 
        this.jobId = jobId; 
        this.seekerId = seekerId; 
        this.resumePath = resumePath; 
        this.stage = stage;
    }
    public String getStage() { return stage; }
    public void setStage(String s) { this.stage = s; }
}
