package com.ssm.maintain.bo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.common.entity.LoginInfo;
import com.ssm.maintain.dao.ILoginInfoDao;

@Service("loginInfoBo")
public class LoginInfoBo implements ILoginInfoBo {
	@Resource
	private ILoginInfoDao loginInfoDao;

	@Override
	public Map<String, Object> loginInfoList(LoginInfo loginInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", loginInfoDao.countLoginInfo(loginInfo.getUserId()));
		map.put("rows", loginInfoDao.queryPageLoginInfo(loginInfo));
		return map;
	}
}
