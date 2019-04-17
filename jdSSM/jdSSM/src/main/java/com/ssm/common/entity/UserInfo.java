package com.ssm.common.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.ssm.common.util.IsNotNum;
import com.ssm.common.util.PasswordRule;

public class UserInfo extends BaseEntity {
	@NotEmpty(message="用户名不能为空")
	@Length(min=4,max=12,message="长度只能在4~12个字符之间")
	@IsNotNum(message="用户名不能是纯数字，请重新输入！")
	@Pattern(regexp="^[\\u4e00-\\u9fa5\\w-]+$",message="格式错误，仅支持汉字、字母、数字、“-”、“_”的组合")
	private String userName;
	
    private byte[] passwordMD5;
    
    @NotEmpty(message="密码不能为空")
    @Length(min=6,max=20,message="长度只能在6~20个字符之间")
    @PasswordRule
    private String password;
    
    @NotEmpty(message="手机不能为空")
    @Pattern(regexp="^\\d+$",message="手机号必须由数字组成")
    @Length(min=11,max=11,message="手机号必须为11位")
    @Pattern(regexp="^1[38][0-9]{9}|15[012356789][0-9]{8}|14[57][0-9]{8}|17[678][0-9]{8}$",message="手机号格式错误")
    private String phone;
    private String loginIp;
    
    public UserInfo() {
		super();
	}
	public UserInfo(String userName, byte[] passwordMD5, String password, String phone, String loginIp) {
		super();
		this.userName = userName;
		this.passwordMD5 = passwordMD5;
		this.password = password;
		this.phone = phone;
		this.loginIp = loginIp;
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
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	@Override
	public String toString() {
		return "userName:" + userName + ", password:" + password + ", phone:" + phone;
	}
}