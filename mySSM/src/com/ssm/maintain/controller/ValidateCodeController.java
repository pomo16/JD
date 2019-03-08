package com.ssm.maintain.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manager/validateCode")
public class ValidateCodeController {
	/**
	 * 生成随机验证码
	 * @return
	 */
	public String createValidateCode() {
		String vcStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		String validateCode = "";
		for (int i = 0; i < 4; i++) {
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
		g.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		g.setColor(new Color(255, 255, 255));
		for (int i = 0; i < validateCode.length(); i++) {
			String vc = String.valueOf(validateCode.charAt(i));
			Random random = new Random();
			int randomNum = random.nextInt(8);
			g.drawString(vc, 12 * (i + 1) + randomNum, 25 + randomNum);
		}
	}

	/**
	 * 生成验证码
	 */
	@RequestMapping("/createValidateCode")
	public void createValidateCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		// 输出背景图
		response.setContentType("image/jpeg");
		String path = request.getSession().getServletContext().getRealPath("/common/images/") + "vc.jpg";
		System.out.println("path=" + path);
		File vcFile = new File(path);
		System.out.println("文件大小：" + vcFile.length());
		BufferedImage image = ImageIO.read(vcFile);

		// 生成验证码
		String validateCode = createValidateCode();

		// 画出验证码
		drawValidateCode(image, validateCode);

		// 将验证码保存到Session中

		session.setAttribute("validateCodeInSession", validateCode);

		// 将背景图以及验证码输出到网页
		OutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpeg", out);
		out.close();
	}

	/**
	 * 验证用户所输入的验证码是否正确
	 */
	@RequestMapping("/checkValidateCode")
	@ResponseBody
	public Object checkValidateCode(String validateCode, HttpSession session) throws Exception {
		if (validateCode.equalsIgnoreCase((String) session.getAttribute("validateCodeInSession"))) {
			return true;
		} else {
			return false;
		}
	}
}
