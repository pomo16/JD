package com.jd.maintain.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jd.common.MD5Encrypt;
import com.jd.common.dao.UserInfoDao;
import com.jd.common.dto.UserInfoDto;

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
		// request.setCharacterEncoding("utf-8");
		
		//用户输入的信息
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String validateCode = request.getParameter("validateCode");
		
		//验证码校验
		HttpSession session = request.getSession();
		String validateCodeInSession = (String)session.getAttribute("validateCodeInSession"); //从Session中获取验证码
		if(validateCode.equalsIgnoreCase(validateCodeInSession)) {
			UserInfoDao userInfoDao = new UserInfoDao();
			RequestDispatcher rd = null;
			//用户名重复校验
			if(userInfoDao.queryByUserName(userName) != null) {
				System.out.println("用戶已存在");
				rd = request.getRequestDispatcher("../alreadyRegister.jsp");
			} else {
				UserInfoDto userInfoDto = new UserInfoDto();
				userInfoDto.setUserName(userName);
				userInfoDto.setPassword(MD5Encrypt.encryptByMD5(password));
				userInfoDto.setPhone(phone);
				userInfoDao.saveUserInfo(userInfoDto);
				System.out.println("用户信息成功保存到数据库");
				rd = request.getRequestDispatcher("../registerSuccess.jsp");
			}
			rd.forward(request, response);
		}else {
			response.sendRedirect("../validateCodeError.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
