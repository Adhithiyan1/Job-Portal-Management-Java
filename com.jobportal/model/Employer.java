package com.jobportal.model;

public class Employer extends User 
{
	private String pocName;
	private String pocEmail;
	private String pocPhone;

	public Employer() { super(); }
	public Employer(int userId, String name, String address, String email, String phone, String password,String pocName, 
		String pocEmail, String pocPhone) 
	{
	super(userId, name, address, email, phone, password);
	this.pocName = pocName; 
	this.pocEmail = pocEmail; 
	this.pocPhone = pocPhone;
	}
	
	@Override public String getRole() { return "EMPLOYER"; }

	public String getPocName() { return pocName; }
}