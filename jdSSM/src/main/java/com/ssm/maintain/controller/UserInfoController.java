package com.ssm.maintain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.ssm.common.entity.Msg;
import com.ssm.common.entity.UserInfo;
import com.ssm.maintain.service.IUserInfoBo;

@Controller
@RequestMapping("/manager/userInfo")
public class UserInfoController {
	
	@Resource
	private IUserInfoBo userInfoBo;
	
	/**
	 * 注册
	 * @param userInfo
	 * @return Msg
	 */
	@RequestMapping("/addUserInfo")
	@ResponseBody
	public Msg addUserInfo(@Valid UserInfo userInfo, BindingResult result) {		
		System.out.println(userInfo.toString());
		//后端校验用户名、密码、电话等信息有错误
		if(result.hasErrors()) {
			//校验失败应该返回失败，在模态框中显示校验失败的错误信息
			MultiValueMap<String, String> multivaluemap = new LinkedMultiValueMap<>();
			Map<String, String> map = new HashMap<>();
			for(FieldError fieldError : result.getFieldErrors()) {
				//System.out.println("错误的字段名：" + fieldError.getField());
				//System.out.println("错误信息：" + fieldError.getDefaultMessage());
				multivaluemap.add(fieldError.getField(),fieldError.getDefaultMessage());
			}
			Set<String> field = multivaluemap.keySet();
			for (String key : field) {
	            List<String> values = multivaluemap.get(key);
	            String valuesStr = StringUtils.join(values.toArray(), "; ");
	            map.put(key,valuesStr);
			}
			return Msg.fail().add("errorFields",map);
		} else {
			//后端校验数据库中有重名
			if(!userInfoBo.userNameValidate(userInfo.getUserName())) {
				System.out.println(userInfoBo.userNameValidate(userInfo.getUserName()));
				Map<String, String> userNameState = new HashMap<>();
				userNameState.put(userInfo.getUserName(),"该用户名已经注册");
				return Msg.fail().add("errorFields",userNameState);
			} else {
				userInfoBo.addUserInfo(userInfo);
				return Msg.success();
			}
		}	
	}
	
	/**
	 * 账号验证
	 * @return Boolean
	 */
	@RequestMapping("/userNameValidate")
	@ResponseBody
	public boolean userNameValidate(@RequestParam("userName") String userName) {
		return userInfoBo.userNameValidate(userName);
	}
	
	/**
	 * 登录
	 * @param userInfo
	 * @return Msg
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Msg login(UserInfo userInfo,HttpServletRequest request, HttpSession session) {
		userInfo.setLoginIp(request.getRemoteAddr());
		return Msg.success().add("loginState",userInfoBo.login(userInfo));
	}
	
	/**
	 * 显示用户信息
	 * @return Map
	 */
	@RequestMapping("/userInfoList")
	@ResponseBody
	public Object userInfoList(UserInfo userInfo) {
		return userInfoBo.userInfoList(userInfo);
	}
	
	/**
	 * 修改用户信息
	 * @return Msg
	 */
	@RequestMapping("/updateUserInfo")
	@ResponseBody
	public Msg updateUserInfo(UserInfo userInfo) {
		userInfoBo.updateById(userInfo);
		return Msg.success();
	}
	
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteByIds")
	@ResponseBody
	public Object deleteByIds(int[] ids) {
		return userInfoBo.deleteByIds(ids);
	}
	
	/**
	 * 退出系统
	 * @return Msg
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public Msg logout(HttpSession session){
		session.invalidate();
		return Msg.success();
	}
}
