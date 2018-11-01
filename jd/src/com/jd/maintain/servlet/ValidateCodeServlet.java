package com.jd.maintain.servlet;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ValidateCode
 */
@WebServlet(name = "ValidateCodeServlet", urlPatterns = { "/maintain/ValidateCodeServlet" })
public class ValidateCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public String createValidateCode() {
    	String vcStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	Random random = new Random();
    	String validateCode = "";
    	for(int i=0;i<4;i++) {
    		int randomNum = random.nextInt(vcStr.length());
    		validateCode += vcStr.charAt(randomNum);
    	}
    	return validateCode;
    }
    
    
    /**
     * 画出验证码
     * @param image
     * @param validateCode
     */
    public void drawValidateCode(BufferedImage image, String validateCode) {
    	Graphics g = image.getGraphics();
    	g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
    	// g.setColor(new Color(255, 0, 0));
    	for(int i=0;i<validateCode.length();i++) {
    		String vc = String.valueOf(validateCode.charAt(i));
    		Random random = new Random();
    		int randomNum = random.nextInt(7);
    		g.drawString(vc, 10*(i+1) + randomNum, 25 + randomNum);
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		// String path = request.getRealPath("/common/image/") + "vc.jpg";
		String path = request.getSession().getServletContext().getRealPath("/common/image/") + "vc.jpg";
		System.out.println("path=" + path);
		File vcFile = new File(path);
		System.out.println("文件大小：" + vcFile.length());
		BufferedImage image = ImageIO.read(vcFile);
		
		//生成验证码
		String validateCode = createValidateCode();
		
		//画出验证码
		drawValidateCode(image, validateCode);
		
		//将验证码保存到Session中
		HttpSession session = request.getSession();
		session.setAttribute("validateCodeInSession", validateCode);
		
		//将背景图以及验证码输出到网页
		OutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpeg", out);
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
