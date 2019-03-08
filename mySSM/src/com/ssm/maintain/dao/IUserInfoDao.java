package com.ssm.maintain.dao;

import java.util.List;

import com.ssm.common.entity.UserInfo;


public interface IUserInfoDao {
	//插入用户
	int insert(UserInfo userInfo);
	
	//根据账号查询用户
	UserInfo queryByUserName(String userName);
	
	//分页查询用户
	List<UserInfo> queryPageUserInfo(UserInfo userInfo);
	
	//统计用户
	int countUserInfo();
	
}
