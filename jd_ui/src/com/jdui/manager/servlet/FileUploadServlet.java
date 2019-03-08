package com.jdui.manager.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.sf.json.JSONObject;


/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/manager/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pictureName = request.getParameter("pictureName");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		PrintWriter out = response.getWriter();
		try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField() == false) {
					String fileName = "";
					if (pictureName != null) {
						fileName = pictureName;
					} else {
						fileName = item.getName();
						if (!"".equals(fileName)) {
							fileName = new Date().getTime() + fileName.substring(fileName.lastIndexOf("."));
						}
					}
					String savePath = request.getSession().getServletContext().getRealPath("/common/image/product");
					System.out.println(savePath + "/" + fileName);
					File saveFile = new File(savePath + "/" + fileName);
					item.write(saveFile);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("key", "ok");
					map.put("fileName", fileName);
					JSONObject object = JSONObject.fromObject(map);
					out.print(object.toString());
					out.close();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
