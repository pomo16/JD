package com.ssm.maintain.dao;

import java.util.List;

import com.ssm.common.entity.Product;

public interface IProductDao {
	// ������Ʒ
	int insert(Product product);
	
	// ��ȡ������Ʒ
	List<Product> getAllProduct();

	// ͳ����Ʒ
	int countProduct();

	// ���ݱ�Ų�ѯ��Ʒ
	Product queryByProductCode(String productCode);

	// ����ID�޸���Ʒ
	int updateById(Product product);

	// ����IDɾ����Ʒ
	int deleteByIds(int[] ids);
}