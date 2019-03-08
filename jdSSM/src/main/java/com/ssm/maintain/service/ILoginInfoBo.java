package com.ssm.maintain.service;

import java.util.Map;

import com.ssm.common.entity.LoginInfo;

public interface ILoginInfoBo {
	/**
	 * œ‘ æµ«¬º–≈œ¢
	 * @param loginInfo
	 * @return
	 */
	public Map<String,Object> loginInfoList(LoginInfo loginInfo);
	
}
