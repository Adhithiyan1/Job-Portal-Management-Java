package com.jobportal.model;

public class Admin extends User {
    public Admin() { super(); }
    
    public Admin(int userId, String name, String address, String email, String phone, String password) {
        super(userId, name, address, email, phone, password);
    }
    
    @Override 
    public String getRole() { return "ADMIN"; }
}
