package com.ssm.maintain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.common.entity.LoginInfo;
import com.ssm.maintain.dao.ILoginInfoDao;

@Service("loginInfoBo")
public class LoginInfoBo implements ILoginInfoBo {
	@Autowired
	private ILoginInfoDao loginInfoDao;

	@Override
	public Map<String, Object> loginInfoList(LoginInfo loginInfo) {
		PageHelper.startPage(loginInfo.getPage(), loginInfo.getRows());
		PageHelper.orderBy("id asc");
		List<LoginInfo> allLoginInfo = loginInfoDao.getAllLoginInfo(loginInfo.getUserId());
		PageInfo page = new PageInfo(allLoginInfo, loginInfo.getRows());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", loginInfoDao.countLoginInfo(loginInfo.getUserId()));
		map.put("rows", page.getList());
		return map;
	}
}
