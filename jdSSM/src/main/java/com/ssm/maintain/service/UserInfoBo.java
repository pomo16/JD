package com.ssm.maintain.service;


import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.common.entity.LoginInfo;
import com.ssm.common.entity.Product;
import com.ssm.common.entity.UserInfo;
import com.ssm.common.util.MD5Encrypt;
import com.ssm.maintain.dao.ILoginInfoDao;
import com.ssm.maintain.dao.IUserInfoDao;

@Service("UserInfoBo")
public class UserInfoBo implements IUserInfoBo{
	
	@Autowired
	private IUserInfoDao userInfoDao;
	
	@Autowired
	private ILoginInfoDao loginInfoDao;
	
	@Override
	public void addUserInfo(UserInfo userInfo) {
		userInfo.setPasswordMD5(MD5Encrypt.encryptByMD5(userInfo.getPassword()));
		userInfoDao.insert(userInfo);
	}
	
	@Override
	public boolean userNameValidate(String userName) {
		if(userInfoDao.queryByUserName(userName)==null){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public String login(UserInfo userInfo) {
		UserInfo userInfoInDB = userInfoDao.queryByUserName(userInfo.getUserName());
		if(userInfoInDB == null){
			return "loginCodeError";
		}else if(!MD5Encrypt.validatePassword(userInfo.getPassword(), userInfoInDB.getPasswordMD5())){	
			return "passwordError";
		}else{
			LoginInfo loginInfo = new LoginInfo();
			loginInfo.setUserId(userInfoInDB.getId());
			loginInfo.setLoginTime(new Timestamp(new Date().getTime()));
			loginInfo.setLoginIp(userInfo.getLoginIp());
			loginInfoDao.insert(loginInfo);
			return "ok";
		}
	}
	
	@Override
	public Map<String, Object> userInfoList(UserInfo userInfo) {
		PageHelper.startPage(userInfo.getPage(), userInfo.getRows());
		PageHelper.orderBy("id asc");
		List<UserInfo> allUsers = userInfoDao.getAllUserInfo();
		PageInfo page = new PageInfo(allUsers, userInfo.getRows());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", userInfoDao.countUserInfo());
		map.put("rows", page.getList());
		return map;
	}
	
	@Override
	public void updateById(UserInfo userInfo) {
		userInfoDao.updateById(userInfo);
	}

	@Override
	public int deleteByIds(int[] ids) {
		userInfoDao.deleteloginByIds(ids);
		return userInfoDao.deleteByIds(ids);
	}
	
}
