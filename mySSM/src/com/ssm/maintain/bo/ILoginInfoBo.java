package com.ssm.maintain.bo;

import java.util.Map;

import com.ssm.common.entity.LoginInfo;

public interface ILoginInfoBo {
	/**
	 * ��ʾ��¼��Ϣ
	 * @param loginInfo
	 * @return
	 */
	public Map<String,Object> loginInfoList(LoginInfo loginInfo);
	
}
