package com.jobportal.model;

public abstract class User {
    protected int userId;
    protected String name;
    protected String address;
    protected String email;
    protected String phone;
    protected String password;

    public User() {}

    public User(int userId, String name, String address, String email, String phone, String password) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    // Getters
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getPassword() { return password; }

    // Setters
    public void setUserId(int userId) { this.userId = userId; }
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setPassword(String password) { this.password = password; }

    public abstract String getRole();
}