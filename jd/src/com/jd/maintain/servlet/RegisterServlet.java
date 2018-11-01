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
		
		//�û��������Ϣ
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String validateCode = request.getParameter("validateCode");
		
		//��֤��У��
		HttpSession session = request.getSession();
		String validateCodeInSession = (String)session.getAttribute("validateCodeInSession"); //��Session�л�ȡ��֤��
		if(validateCode.equalsIgnoreCase(validateCodeInSession)) {
			UserInfoDao userInfoDao = new UserInfoDao();
			RequestDispatcher rd = null;
			//�û����ظ�У��
			if(userInfoDao.queryByUserName(userName) != null) {
				System.out.println("�Ñ��Ѵ���");
				rd = request.getRequestDispatcher("../alreadyRegister.jsp");
			} else {
				UserInfoDto userInfoDto = new UserInfoDto();
				userInfoDto.setUserName(userName);
				userInfoDto.setPassword(MD5Encrypt.encryptByMD5(password));
				userInfoDto.setPhone(phone);
				userInfoDao.saveUserInfo(userInfoDto);
				System.out.println("�û���Ϣ�ɹ����浽���ݿ�");
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
