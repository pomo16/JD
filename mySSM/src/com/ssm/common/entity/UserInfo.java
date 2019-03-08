package com.ssm.common.entity;

import java.io.Serializable;

public class UserInfo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userName;
    private byte[] passwordMD5; 
    private String password;
    private String phone;
    private String loginMessage;
    private String loginIp;
	public UserInfo() {
		super();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}	
	public byte[] getPasswordMD5() {
		return passwordMD5;
	}
	public void setPasswordMD5(byte[] passwordMD5) {
		this.passwordMD5 = passwordMD5;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLoginMessage() {
		return loginMessage;
	}
	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
}