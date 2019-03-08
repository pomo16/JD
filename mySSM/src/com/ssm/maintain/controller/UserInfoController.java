package com.ssm.maintain.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.common.entity.UserInfo;
import com.ssm.maintain.bo.IUserInfoBo;

@Controller
@RequestMapping("/manager/userInfo")
public class UserInfoController {
	@Resource
	private IUserInfoBo userInfoBo;
	
	/**
	 * ע��
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("/addUserInfo")
	@ResponseBody
	public Object addUserInfo(UserInfo userInfo) {		
		userInfoBo.addUserInfo(userInfo);
		return "ok";
	}
	
	/**
	 * ��¼
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Object login(UserInfo userInfo,HttpServletRequest request, HttpSession session) {	
		userInfo.setLoginIp(request.getRemoteAddr());
		userInfo = userInfoBo.login(userInfo);
		if("ok".equals(userInfo.getLoginMessage())){	
			session.setAttribute("userInfo", userInfo);
		}	
		return userInfo;
	}
	
	/**
	 * ��ʾ�û���Ϣ
	 * @return
	 */
	@RequestMapping("/userInfoList")
	@ResponseBody
	public Object userInfoList(UserInfo userInfo) {
		return userInfoBo.userInfoList(userInfo);
	}
	
	/**
	 * �˺���֤
	 * @return
	 */
	@RequestMapping("/userNameValidate")
	@ResponseBody
	public Object userNameValidate(String userName) {
		return userInfoBo.userNameValidate(userName);
	}	
	
	/**
	 * �˳�ϵͳ
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public Object logout(HttpSession session){
		session.invalidate();
		return "ok";
	}
}
