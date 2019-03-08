package com.ssm.common.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/common/util")
public class UtilController {
	/**
	 * 文件上传
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public Object upload(MultipartFile uploadFile,HttpSession session,String fileSaveName) {		
		String path = session.getServletContext().getRealPath("/common/images/product");
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		String originalFilename = uploadFile.getOriginalFilename();
		if(fileSaveName==null || "".equals(fileSaveName)){
			  fileSaveName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
		}      
        File file = new File(path, fileSaveName);
        try {
        	uploadFile.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", "ok");
		map.put("fileName", fileSaveName);
		return map;
	}
}
