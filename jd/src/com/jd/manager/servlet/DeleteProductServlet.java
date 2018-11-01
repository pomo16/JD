package com.jd.manager.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jd.common.dao.ProductDao;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/manager/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String picture = request.getParameter("picture");
		
		ProductDao productDao = new ProductDao();
		productDao.deleteProduct(id);
		if(!"".equals(picture)) {
			String savePath = request.getSession().getServletContext().getRealPath("/common/image/product");
			System.out.println(savePath);
			File pictureFile = new File(savePath + "/" + picture);
			pictureFile.delete();
		}
		RequestDispatcher rd = request.getRequestDispatcher("QueryProductServlet?page=0");
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
