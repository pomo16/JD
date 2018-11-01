package com.jd.common.dto;

import java.sql.Timestamp;

public class LoginInfoDto {
	private int id;
	private int userId;
	private Timestamp loginTime;
	private String loginTimeStr;
	private String loginIp;
	public LoginInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LoginInfoDto(int id, int userId, Timestamp loginTime, String loginTimeStr, String loginIp) {
		super();
		this.id = id;
		this.userId = userId;
		this.loginTime = loginTime;
		this.loginTimeStr = loginTimeStr;
		this.loginIp = loginIp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginTimeStr() {
		return loginTimeStr;
	}

	public void setLoginTimeStr(String loginTimeStr) {
		this.loginTimeStr = loginTimeStr;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	
}
