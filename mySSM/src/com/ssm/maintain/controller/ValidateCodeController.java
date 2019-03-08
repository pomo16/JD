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
	 * ���������֤��
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
	 * ������֤��
	 * @param image
	 * @param validateCode
	 */
	public void drawValidateCode(BufferedImage image, String validateCode) {
		Graphics g = image.getGraphics();
		g.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		g.setColor(new Color(255, 255, 255));
		for (int i = 0; i < validateCode.length(); i++) {
			String vc = String.valueOf(validateCode.charAt(i));
			Random random = new Random();
			int randomNum = random.nextInt(8);
			g.drawString(vc, 12 * (i + 1) + randomNum, 25 + randomNum);
		}
	}

	/**
	 * ������֤��
	 */
	@RequestMapping("/createValidateCode")
	public void createValidateCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		// �������ͼ
		response.setContentType("image/jpeg");
		String path = request.getSession().getServletContext().getRealPath("/common/images/") + "vc.jpg";
		System.out.println("path=" + path);
		File vcFile = new File(path);
		System.out.println("�ļ���С��" + vcFile.length());
		BufferedImage image = ImageIO.read(vcFile);

		// ������֤��
		String validateCode = createValidateCode();

		// ������֤��
		drawValidateCode(image, validateCode);

		// ����֤�뱣�浽Session��

		session.setAttribute("validateCodeInSession", validateCode);

		// ������ͼ�Լ���֤���������ҳ
		OutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpeg", out);
		out.close();
	}

	/**
	 * ��֤�û����������֤���Ƿ���ȷ
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
