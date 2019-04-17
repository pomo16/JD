package com.ssm.maintain.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.common.entity.LoginInfo;
import com.ssm.maintain.service.ILoginInfoBo;

@Controller
@RequestMapping("/manager/loginInfo")
public class LoginInfoController {
	@Resource
	private ILoginInfoBo loginInfoBo;
	
	/**
	 * œ‘ æµ«¬º–≈œ¢
	 * @return
	 */
	@RequestMapping("/loginInfoList")
	@ResponseBody
	public Object loginInfoList(LoginInfo loginInfo) {
		return loginInfoBo.loginInfoList(loginInfo);
	}
}
