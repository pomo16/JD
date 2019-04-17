package com.ssm.maintain.dao;

import java.util.List;

import com.ssm.common.entity.LoginInfo;

public interface ILoginInfoDao {
	// ��ҳ��ѯ��¼��Ϣ
	List<LoginInfo> getAllLoginInfo(int userId);

	// ͳ�Ƶ�¼����
	int countLoginInfo(int userId);
	
	//�����¼��Ϣ
	int insert(LoginInfo loginInfo);
}
