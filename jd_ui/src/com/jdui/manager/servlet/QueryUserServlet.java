package com.jdui.manager.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdui.common.dao.UserInfoDao;
import com.jdui.common.dto.UserInfoDto;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class QueryUserServlet
 */
@WebServlet("/manager/QueryUserServlet")
public class QueryUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		int rows = Integer.parseInt(request.getParameter("rows"));

		UserInfoDao userInfoDao = new UserInfoDao();
		List<UserInfoDto> userList = userInfoDao.queryPageUser((page - 1)*rows, rows);
		int totalUser = userInfoDao.countUser();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", totalUser);
		map.put("rows", userList);
		PrintWriter out = response.getWriter();
		JSONObject object = JSONObject.fromObject(map);
		out.print(object.toString());
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
