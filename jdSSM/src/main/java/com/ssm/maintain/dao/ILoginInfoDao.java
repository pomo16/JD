package com.ssm.maintain.dao;

import java.util.List;

import com.ssm.common.entity.LoginInfo;

public interface ILoginInfoDao {
	// 分页查询登录信息
	List<LoginInfo> getAllLoginInfo(int userId);

	// 统计登录数量
	int countLoginInfo(int userId);
	
	//插入登录信息
	int insert(LoginInfo loginInfo);
}
