package com.ssm.maintain.service;

import java.util.Map;

import com.ssm.common.entity.UserInfo;

public interface IUserInfoBo {
	/**
	 * ����û�
	 * @param userInfo
	 */
	public void addUserInfo(UserInfo userInfo);
	
	/**
	 * �˺���֤
	 * @param userName
	 * @return
	 */
	public boolean userNameValidate(String userName);
	
	/**
	 * �û���¼
	 * @param userInfo
	 */
	public String login(UserInfo userInfo);
	
	/**
	 * ��ʾ�û���Ϣ
	 * @param userInfo
	 * @return
	 */
	public Map<String,Object> userInfoList(UserInfo userInfo);
	
	/**
	 * ����ID�޸��û�
	 * 
	 * @param product
	 */
	public void updateById(UserInfo userInfo);
	
	/**
	 * ����idsɾ���û�
	 * 
	 * @param ids
	 * @return
	 */
	public int deleteByIds(int[] ids);
}
