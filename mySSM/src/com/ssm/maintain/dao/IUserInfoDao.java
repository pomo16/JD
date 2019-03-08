package com.ssm.maintain.dao;

import java.util.List;

import com.ssm.common.entity.UserInfo;


public interface IUserInfoDao {
	//�����û�
	int insert(UserInfo userInfo);
	
	//�����˺Ų�ѯ�û�
	UserInfo queryByUserName(String userName);
	
	//��ҳ��ѯ�û�
	List<UserInfo> queryPageUserInfo(UserInfo userInfo);
	
	//ͳ���û�
	int countUserInfo();
	
}
