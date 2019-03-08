package com.ssm.maintain.dao;

import java.util.List;

import com.ssm.common.entity.Product;
import com.ssm.common.entity.UserInfo;

public interface IUserInfoDao {
	//�����û�
	int insert(UserInfo userInfo);
	
	//�����˺Ų�ѯ�û�
	UserInfo queryByUserName(String userName);
		
	//ͳ���û�
	int countUserInfo();
	
	//��ȡ�����û�
	List<UserInfo> getAllUserInfo();
	
	// ����ID�޸��û�
	int updateById(UserInfo userInfo);

	// ����IDɾ���û�
	int deleteByIds(int[] ids);	
	
	// ����IDɾ���û���¼��Ϣ
	int deleteloginByIds(int[] ids);
}
