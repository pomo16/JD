package com.ssm.maintain.bo;

import java.util.Map;

import com.ssm.common.entity.UserInfo;

public interface IUserInfoBo {
	/**
	 * 添加用户
	 * @param userInfo
	 */
	public void addUserInfo(UserInfo userInfo);
	
	/**
	 * 用户登录
	 * @param userInfo
	 */
	public UserInfo login(UserInfo userInfo);
	
	/**
	 * 显示用户信息
	 * @param userInfo
	 * @return
	 */
	public Map<String,Object> userInfoList(UserInfo userInfo);
	
	/**
	 * 账号验证
	 * @param userName
	 * @return
	 */
	public boolean userNameValidate(String userName);
	
	
}
