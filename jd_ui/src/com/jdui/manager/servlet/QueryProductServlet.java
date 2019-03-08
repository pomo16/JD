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

import com.jdui.common.dao.ProductDao;
import com.jdui.common.dto.ProductDto;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class QueryProductServlet
 */
@WebServlet("/manager/QueryProductServlet")
public class QueryProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryProductServlet() {
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
		ProductDao productDao = new ProductDao();
		List<ProductDto> productList = productDao.queryPageProduct((page-1)*rows, rows);
		int totalProduct = productDao.countProduct();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", totalProduct);
		map.put("rows", productList);
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
