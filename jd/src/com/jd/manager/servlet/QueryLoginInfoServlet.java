package com.jd.manager.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jd.common.dao.LoginInfoDao;
import com.jd.common.dto.LoginInfoDto;

/**
 * Servlet implementation class QueryLoginInfoServlet
 */
@WebServlet("/manager/QueryLoginInfoServlet")
public class QueryLoginInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int rows = 8;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryLoginInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId = Integer.parseInt(request.getParameter("userId"));
		int page = Integer.parseInt(request.getParameter("page"));
		int pageStart = page * rows;
		LoginInfoDao loginInfoDao = new LoginInfoDao();
		List<LoginInfoDto> loginInfoList = loginInfoDao.queryPageLoginInfo(userId,pageStart,rows);
		int totalLoginInfo = loginInfoDao.countLoginInfo(userId);
		//×ÜÒ³Êý
		int totalPage = 0;
		if(totalLoginInfo % rows == 0) {
			totalPage = totalLoginInfo / rows;
		}else {
			totalPage = totalLoginInfo / rows + 1;
		}
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("loginInfoList", loginInfoList);
		request.setAttribute("rows", rows);
		RequestDispatcher rd = request.getRequestDispatcher("loginInfo.jsp");
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
