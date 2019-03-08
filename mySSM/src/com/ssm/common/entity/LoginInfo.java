package com.ssm.common.entity;

import java.sql.Timestamp;

public class LoginInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private int userId;
	private Timestamp loginTime;
	private String loginIp;
	public LoginInfo() {
		super();
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
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
}
