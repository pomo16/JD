package com.jdui.maintain.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdui.common.MD5Encrypt;
import com.jdui.common.dao.LoginInfoDao;
import com.jdui.common.dao.UserInfoDao;
import com.jdui.common.dto.LoginInfoDto;
import com.jdui.common.dto.UserInfoDto;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/maintain/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.setCharacterEncoding("utf-8");
		
		//用户输入信息
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		//用户名存在校验
		UserInfoDao userInfoDao = new UserInfoDao();
		UserInfoDto userInfoDto = userInfoDao.queryByUserName(userName);
		PrintWriter out = response.getWriter();
		if(userInfoDto != null) {
			//密码校验
			if(MD5Encrypt.validatePassword(password, userInfoDto.getPassword())) {
				LoginInfoDto loginInfoDto = new LoginInfoDto();
				loginInfoDto.setUserId(userInfoDto.getId());
				loginInfoDto.setLoginTime(new Timestamp(new Date().getTime()));
				loginInfoDto.setLoginIp(request.getRemoteAddr());
				new LoginInfoDao().saveLoginInfo(loginInfoDto);
				
				HttpSession session = request.getSession();
				session.setAttribute("userInfoDto", userInfoDto);
				out.print("ok");
			} else {
				System.out.println("密码错误");
				out.print("passwordError");
			}
		} else {
			System.out.println("账号错误");
			out.print("userNameError");
		}
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
