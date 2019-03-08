package com.ssm.maintain.bo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.common.entity.LoginInfo;
import com.ssm.common.entity.UserInfo;
import com.ssm.common.util.MD5Encrypt;
import com.ssm.maintain.dao.ILoginInfoDao;
import com.ssm.maintain.dao.IUserInfoDao;

@Service("userInfoBo")
public class UserInfoBo implements IUserInfoBo {
	@Resource
	private IUserInfoDao userInfoDao;
	
	@Resource
	private ILoginInfoDao loginInfoDao;
	
	@Override
	public void addUserInfo(UserInfo userInfo) {
		userInfo.setPasswordMD5(MD5Encrypt.encryptByMD5(userInfo.getPassword()));
		userInfoDao.insert(userInfo);
	}

	@Override
	public UserInfo login(UserInfo userInfo) {
		UserInfo userInfoInDB = userInfoDao.queryByUserName(userInfo.getUserName());	
		if(userInfoInDB == null){
			userInfoInDB = new UserInfo();
			userInfoInDB.setLoginMessage("loginCodeError");
		}else if(!MD5Encrypt.validatePassword(userInfo.getPassword(), userInfoInDB.getPasswordMD5())){		
			userInfoInDB.setLoginMessage("passwordError");
		}else{
			LoginInfo loginInfo = new LoginInfo();
			loginInfo.setUserId(userInfoInDB.getId());
			loginInfo.setLoginTime(new Timestamp(new Date().getTime()));
			loginInfo.setLoginIp(userInfo.getLoginIp());
			loginInfoDao.insert(loginInfo);
			userInfoInDB.setLoginMessage("ok");
		}
		return userInfoInDB;
	}

	@Override
	public Map<String, Object> userInfoList(UserInfo userInfo) {		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", userInfoDao.countUserInfo());
		map.put("rows", userInfoDao.queryPageUserInfo(userInfo));
		return map;
	}

	@Override
	public boolean userNameValidate(String userName) {
		if(userInfoDao.queryByUserName(userName)==null){
			return true;
		}else{
			return false;
		}
	}
	
}
