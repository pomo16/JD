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
	 * ע��
	 * @param userInfo
	 * @return Msg
	 */
	@RequestMapping("/addUserInfo")
	@ResponseBody
	public Msg addUserInfo(@Valid UserInfo userInfo, BindingResult result) {		
		System.out.println(userInfo.toString());
		//���У���û��������롢�绰����Ϣ�д���
		if(result.hasErrors()) {
			//У��ʧ��Ӧ�÷���ʧ�ܣ���ģ̬������ʾУ��ʧ�ܵĴ�����Ϣ
			MultiValueMap<String, String> multivaluemap = new LinkedMultiValueMap<>();
			Map<String, String> map = new HashMap<>();
			for(FieldError fieldError : result.getFieldErrors()) {
				//System.out.println("������ֶ�����" + fieldError.getField());
				//System.out.println("������Ϣ��" + fieldError.getDefaultMessage());
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
			//���У�����ݿ���������
			if(!userInfoBo.userNameValidate(userInfo.getUserName())) {
				System.out.println(userInfoBo.userNameValidate(userInfo.getUserName()));
				Map<String, String> userNameState = new HashMap<>();
				userNameState.put(userInfo.getUserName(),"���û����Ѿ�ע��");
				return Msg.fail().add("errorFields",userNameState);
			} else {
				userInfoBo.addUserInfo(userInfo);
				return Msg.success();
			}
		}	
	}
	
	/**
	 * �˺���֤
	 * @return Boolean
	 */
	@RequestMapping("/userNameValidate")
	@ResponseBody
	public boolean userNameValidate(@RequestParam("userName") String userName) {
		return userInfoBo.userNameValidate(userName);
	}
	
	/**
	 * ��¼
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
	 * ��ʾ�û���Ϣ
	 * @return Map
	 */
	@RequestMapping("/userInfoList")
	@ResponseBody
	public Object userInfoList(UserInfo userInfo) {
		return userInfoBo.userInfoList(userInfo);
	}
	
	/**
	 * �޸��û���Ϣ
	 * @return Msg
	 */
	@RequestMapping("/updateUserInfo")
	@ResponseBody
	public Msg updateUserInfo(UserInfo userInfo) {
		userInfoBo.updateById(userInfo);
		return Msg.success();
	}
	
	/**
	 * ɾ���û�
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteByIds")
	@ResponseBody
	public Object deleteByIds(int[] ids) {
		return userInfoBo.deleteByIds(ids);
	}
	
	/**
	 * �˳�ϵͳ
	 * @return Msg
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public Msg logout(HttpSession session){
		session.invalidate();
		return Msg.success();
	}
}
