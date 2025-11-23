package com.jobportal.model;

public class JobSeeker extends User {
    public JobSeeker() { super(); }
    
    public JobSeeker(int userId, String name, String address, String email, String phone, String password) {
        super(userId, name, address, email, phone, password);
    }
    
    @Override 
    public String getRole() { return "SEEKER"; }
}