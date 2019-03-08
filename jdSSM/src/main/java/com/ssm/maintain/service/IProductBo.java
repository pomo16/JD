package com.ssm.maintain.service;

import java.util.Map;

import com.ssm.common.entity.Product;

public interface IProductBo {
	/**
	 * �����Ʒ
	 * 
	 * @param product
	 */
	public void addProduct(Product product);

	/**
	 * ��ʾ��Ʒ��Ϣ
	 * 
	 * @param product
	 * @return
	 */
	public Map<String, Object> productList(Product product);

	/**
	 * ��Ʒ�����֤
	 * 
	 * @param productCode
	 * @return
	 */
	public boolean productCodeValidate(String productCode);

	/**
	 * ����ID�޸���Ʒ
	 * 
	 * @param product
	 */
	public void updateById(Product product);

	/**
	 * ����idsɾ����Ʒ
	 * 
	 * @param ids
	 * @return
	 */
	public int deleteByIds(int[] ids);
}
