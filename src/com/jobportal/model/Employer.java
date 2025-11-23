package com.jobportal.model;

public class Employer extends User {
    private String pocName;
    private String pocEmail;
    private String pocPhone;

    public Employer() { super(); }
    
    public Employer(int userId, String name, String address, String email, String phone, String password,
                    String pocName, String pocEmail, String pocPhone) {
        super(userId, name, address, email, phone, password);
        this.pocName = pocName; 
        this.pocEmail = pocEmail; 
        this.pocPhone = pocPhone;
    }

    @Override 
    public String getRole() { return "EMPLOYER"; }

    // Getters
    public String getPocName() { return pocName; }
    public String getPocEmail() { return pocEmail; }
    public String getPocPhone() { return pocPhone; }

    // Setters
    public void setPocName(String pocName) { this.pocName = pocName; }
    public void setPocEmail(String pocEmail) { this.pocEmail = pocEmail; }
    public void setPocPhone(String pocPhone) { this.pocPhone = pocPhone; }
}