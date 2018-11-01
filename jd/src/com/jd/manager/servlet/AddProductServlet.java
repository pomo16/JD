package com.jd.manager.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jd.common.dao.ProductDao;
import com.jd.common.dto.ProductDto;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/manager/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*String productCode = request.getParameter("productCode");
		String productName = request.getParameter("productName");
		float price = Float.parseFloat(request.getParameter("price"));
		float weight = Float.parseFloat(request.getParameter("weight"));
		String place = request.getParameter("place");
		String description = request.getParameter("description");*/
		ProductDto productDto = pictureUpload(request);
		/*productDto.setProductCode(productCode);
		productDto.setProductName(productName);
		productDto.setPrice(price);
		productDto.setWeight(weight);
		productDto.setPlace(place);
		productDto.setDescription(description);*/
		if(productDto != null) {
			ProductDao productDao = new ProductDao();
			productDao.saveProduct(productDto);
			
			RequestDispatcher rd = request.getRequestDispatcher("QueryProductServlet?page=0");
			rd.forward(request, response);
		}else {
			response.sendRedirect("addProduct.jsp?flag=uploadError");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public ProductDto pictureUpload(HttpServletRequest request) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(1024 * 1024 * 2);
		upload.setHeaderEncoding("utf-8");
		ProductDto productDto = new ProductDto();
		try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					String fieldName = item.getFieldName();
					if("productCode".equals(fieldName)) {
						productDto.setProductCode(item.getString("utf-8"));
					} else if ("productName".equals(fieldName)) {
						productDto.setProductName(item.getString("utf-8"));
					} else if ("price".equals(fieldName)) {
						productDto.setPrice(Float.parseFloat(item.getString("utf-8")));
					} else if ("weight".equals(fieldName)) {
						productDto.setWeight(Float.parseFloat(item.getString("utf-8")));
					} else if ("place".equals(fieldName)) {
						productDto.setPlace(item.getString("utf-8"));
					} else if ("description".equals(fieldName)) {
						productDto.setDescription(item.getString("utf-8"));
					}
				} else {
					String fileName = item.getName();
					//System.out.println(fileName);
					if (!"".equals(fileName)) {
						//找不到即不存在，就返回-1
						if (fileName.lastIndexOf(".jpg") != -1 || fileName.lastIndexOf(".png")  != -1) {
							fileName = new Date().getTime() + fileName.substring(fileName.lastIndexOf("."));
							String savePath = request.getSession().getServletContext().getRealPath("/common/image/product");
							System.out.println(savePath);
							File saveFile = new File(savePath + "/" + fileName);
							item.write(saveFile);
							productDto.setPicture(fileName);
						}else {
							return null;
						}
					} 
				}
			}
		} catch (FileUploadException e) {
			//e.printStackTrace();
			System.out.println("图片超过2MB");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productDto;
	}

}
