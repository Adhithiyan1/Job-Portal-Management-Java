package com.jobportal.model;

public abstract class User {
	protected int userId;
	protected String name;
	protected String address;
	protected String email;
	protected String phone;
	protected String password;

	public User() {}


		public User(int userId, String name, String address, String email, String phone, String password) 
		{
			this.userId = userId;
			this.name = name;
			this.address = address;
			this.email = email;
			this.phone = phone;
			this.password = password;
		}


	public int getUserId() { return userId; }
	public String getName() { return name; }
	public String getEmail() { return email; }
	public String getPassword() { return password; }


	public void setPassword(String password) { this.password = password; }


	public abstract String getRole();
}