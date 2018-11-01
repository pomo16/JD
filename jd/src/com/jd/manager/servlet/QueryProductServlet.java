package com.jd.manager.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jd.common.dao.ProductDao;
import com.jd.common.dto.ProductDto;

/**
 * Servlet implementation class QueryProductServlet
 */
@WebServlet("/manager/QueryProductServlet")
public class QueryProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int rows = 8;
       
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
		int pageStart = page * rows;
		ProductDao productDao = new ProductDao();
		List<ProductDto> productList = productDao.queryPageProduct(pageStart, rows);
		int totalProduct = productDao.countProduct();
		//×ÜÒ³Êý
		int totalPage = 0;
		if(totalProduct % rows == 0) {
			totalPage = totalProduct / rows;
		}else {
			totalPage = totalProduct / rows + 1;
		}
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("productList", productList);
		request.setAttribute("rows", rows);
		request.setAttribute("time", new Date().getTime());
		RequestDispatcher rd = request.getRequestDispatcher("product.jsp");
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
