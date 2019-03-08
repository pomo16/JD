package com.ssm.maintain.service;

import java.util.Map;

import com.ssm.common.entity.UserInfo;

public interface IUserInfoBo {
	/**
	 * 添加用户
	 * @param userInfo
	 */
	public void addUserInfo(UserInfo userInfo);
	
	/**
	 * 账号验证
	 * @param userName
	 * @return
	 */
	public boolean userNameValidate(String userName);
	
	/**
	 * 用户登录
	 * @param userInfo
	 */
	public String login(UserInfo userInfo);
	
	/**
	 * 显示用户信息
	 * @param userInfo
	 * @return
	 */
	public Map<String,Object> userInfoList(UserInfo userInfo);
	
	/**
	 * 根据ID修改用户
	 * 
	 * @param product
	 */
	public void updateById(UserInfo userInfo);
	
	/**
	 * 根据ids删除用户
	 * 
	 * @param ids
	 * @return
	 */
	public int deleteByIds(int[] ids);
}
