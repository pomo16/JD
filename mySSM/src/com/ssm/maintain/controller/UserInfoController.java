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
	 * 注册
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
	 * 登录
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
	 * 显示用户信息
	 * @return
	 */
	@RequestMapping("/userInfoList")
	@ResponseBody
	public Object userInfoList(UserInfo userInfo) {
		return userInfoBo.userInfoList(userInfo);
	}
	
	/**
	 * 账号验证
	 * @return
	 */
	@RequestMapping("/userNameValidate")
	@ResponseBody
	public Object userNameValidate(String userName) {
		return userInfoBo.userNameValidate(userName);
	}	
	
	/**
	 * 退出系统
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public Object logout(HttpSession session){
		session.invalidate();
		return "ok";
	}
}
