package com.pbs.ls.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login
{
	@Id
	@Column(name="userid")
	private long userId;
	@Column(name="username")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="designation")
	private String designation;
	public Login() 
	{
	}
	public Login(long userId, String userName, String password, String designation) 
	{
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.designation = designation;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
}
