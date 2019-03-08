package com.jdui.manager.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdui.common.dao.ProductDao;
import com.jdui.common.dto.ProductDto;



/**
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet("/manager/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String productName = request.getParameter("productName");
		float price = Float.parseFloat(request.getParameter("price"));
		float weight = Float.parseFloat(request.getParameter("weight"));
		String place = request.getParameter("place");
		String description = request.getParameter("description");
		String picture = request.getParameter("picture");
		ProductDto productDto = new ProductDto();
		productDto.setId(id);
		productDto.setProductName(productName);
		productDto.setPrice(price);
		productDto.setWeight(weight);
		productDto.setPlace(place);
		productDto.setDescription(description);
		productDto.setPicture(picture);
		ProductDao productDao = new ProductDao();
		productDao.updateProduct(productDto);
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
