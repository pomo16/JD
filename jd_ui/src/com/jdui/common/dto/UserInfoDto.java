package com.jdui.common.dto;

public class UserInfoDto {
	private int id;
	private String userName;
	private byte[] password;
	private String phone;

	public UserInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserInfoDto(int id, String userName, byte[] password, String phone) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public static void main(String[] args) {
		UserInfoDto dto = new UserInfoDto();
		dto.setUserName("tom");
		System.out.println(dto.getUserName());
	}
}
