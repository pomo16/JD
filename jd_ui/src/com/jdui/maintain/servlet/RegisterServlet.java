package com.jdui.maintain.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdui.common.MD5Encrypt;
import com.jdui.common.dao.UserInfoDao;
import com.jdui.common.dto.UserInfoDto;



/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/maintain/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 用户输入的信息
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");

		UserInfoDao userInfoDao = new UserInfoDao();

		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.setUserName(userName);
		userInfoDto.setPassword(MD5Encrypt.encryptByMD5(password));
		userInfoDto.setPhone(phone);
		userInfoDao.saveUserInfo(userInfoDto);
		System.out.println("用户信息成功保存到数据库");
		PrintWriter out = response.getWriter();
		out.print("ok");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
