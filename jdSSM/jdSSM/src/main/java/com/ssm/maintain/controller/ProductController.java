package com.ssm.maintain.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.common.entity.Msg;
import com.ssm.common.entity.Product;
import com.ssm.maintain.service.IProductBo;

@Controller
@RequestMapping("/manager/product")
public class ProductController {
	@Resource
	private IProductBo productBo;

	/**
	 * 添加商品
	 * 
	 * @param Product
	 * @return
	 */
	@RequestMapping("/addProduct")
	@ResponseBody
	public Msg addProduct(Product product) {
		productBo.addProduct(product);
		return Msg.success();
	}

	/**
	 * 显示商品信息
	 * 
	 * @return
	 */
	@RequestMapping("/productList")
	@ResponseBody
	public Object productList(Product product) {
		return productBo.productList(product);
	}

	/**
	 * 商品编号验证
	 * 
	 * @return
	 */
	@RequestMapping("/productCodeValidate")
	@ResponseBody
	public boolean productCodeValidate(String productCode) {
		return productBo.productCodeValidate(productCode);
	}

	/**
	 * 修改商品
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping("/updateById")
	@ResponseBody
	public Msg updateById(Product product) {
		productBo.updateById(product);
		return Msg.success();
	}

	/**
	 * 删除
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
