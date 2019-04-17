package com.ssm.maintain.dao;

import java.util.List;

import com.ssm.common.entity.Product;
import com.ssm.common.entity.UserInfo;

public interface IUserInfoDao {
	//插入用户
	int insert(UserInfo userInfo);
	
	//根据账号查询用户
	UserInfo queryByUserName(String userName);
		
	//统计用户
	int countUserInfo();
	
	//获取所有用户
	List<UserInfo> getAllUserInfo();
	
	// 根据ID修改用户
	int updateById(UserInfo userInfo);

	// 根据ID删除用户
	int deleteByIds(int[] ids);	
	
	// 根据ID删除用户登录信息
	int deleteloginByIds(int[] ids);
}
