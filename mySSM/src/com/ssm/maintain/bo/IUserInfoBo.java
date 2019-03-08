package com.ssm.maintain.bo;

import java.util.Map;

import com.ssm.common.entity.UserInfo;

public interface IUserInfoBo {
	/**
	 * ����û�
	 * @param userInfo
	 */
	public void addUserInfo(UserInfo userInfo);
	
	/**
	 * �û���¼
	 * @param userInfo
	 */
	public UserInfo login(UserInfo userInfo);
	
	/**
	 * ��ʾ�û���Ϣ
	 * @param userInfo
	 * @return
	 */
	public Map<String,Object> userInfoList(UserInfo userInfo);
	
	/**
	 * �˺���֤
	 * @param userName
	 * @return
	 */
	public boolean userNameValidate(String userName);
	
	
}
