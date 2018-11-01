package com.jd.manager.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jd.common.dao.UserInfoDao;
import com.jd.common.dto.UserInfoDto;

/**
 * Servlet implementation class QueryUserServlet
 */
@WebServlet("/manager/QueryUserServlet")
public class QueryUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int rows = 8;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int page = Integer.parseInt(request.getParameter("page"));
		int pageStart = page * rows;
		UserInfoDao userInfoDao = new UserInfoDao();
		List<UserInfoDto> userList = userInfoDao.queryPageUser(pageStart, rows);
		int totalUser = userInfoDao.countUser();
		//×ÜÒ³Êý
		int totalPage = 0;
		if(totalUser % rows == 0) {
			totalPage = totalUser / rows;
		}else {
			totalPage = totalUser / rows + 1;
		}
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("userList", userList);
		request.setAttribute("rows", rows);
		RequestDispatcher rd = request.getRequestDispatcher("userInfo.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
