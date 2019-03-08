package com.ssm.maintain.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.common.entity.Product;
import com.ssm.maintain.bo.IProductBo;

@Controller
@RequestMapping("/manager/product")
public class ProductController {
	@Resource
	private IProductBo productBo;

	/**
	 * �����Ʒ
	 * 
	 * @param Product
	 * @return
	 */
	@RequestMapping("/addProduct")
	@ResponseBody
	public Object addProduct(Product product) {
		productBo.addProduct(product);
		return "ok";
	}

	/**
	 * ��ʾ��Ʒ��Ϣ
	 * 
	 * @return
	 */
	@RequestMapping("/productList")
	@ResponseBody
	public Object productList(Product product) {
		return productBo.productList(product);
	}

	/**
	 * ��Ʒ�����֤
	 * 
	 * @return
	 */
	@RequestMapping("/productCodeValidate")
	@ResponseBody
	public Object productCodeValidate(String productCode) {
		return productBo.productCodeValidate(productCode);
	}

	/**
	 * �޸���Ʒ
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping("/updateById")
	@ResponseBody
	public Object updateById(Product product) {
		productBo.updateById(product);
		return "ok";
	}

	/**
	 * ɾ��
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteByIds")
	@ResponseBody
	public Object deleteByIds(int[] ids) {
		return productBo.deleteByIds(ids);
	}
}
