package com.ssm.maintain.service;

import java.util.Map;

import com.ssm.common.entity.Product;

public interface IProductBo {
	/**
	 * 添加商品
	 * 
	 * @param product
	 */
	public void addProduct(Product product);

	/**
	 * 显示商品信息
	 * 
	 * @param product
	 * @return
	 */
	public Map<String, Object> productList(Product product);

	/**
	 * 商品编号验证
	 * 
	 * @param productCode
	 * @return
	 */
	public boolean productCodeValidate(String productCode);

	/**
	 * 根据ID修改商品
	 * 
	 * @param product
	 */
	public void updateById(Product product);

	/**
	 * 根据ids删除商品
	 * 
	 * @param ids
	 * @return
	 */
	public int deleteByIds(int[] ids);
}
