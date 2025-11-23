package com.jobportal.model;

public class Resume {
    private String path;
    
    public Resume(String path) { 
        this.path = path; 
    }
    
    public String getPath() { 
        return path; 
    }
    
    public void setPath(String path) { 
        this.path = path; 
    }
}