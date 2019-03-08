package com.ssm.maintain.dao;

import java.util.List;

import com.ssm.common.entity.Product;

public interface IProductDao {
	// 插入商品
	int insert(Product product);

	// 分页查询商品
	List<Product> queryPageProduct(Product product);

	// 统计商品
	int countProduct();

	// 根据编号查询商品
	Product queryByProductCode(String productCode);

	// 根据ID修改商品
	int updateById(Product product);

	// 根据ID删除商品
	int deleteByIds(int[] ids);
}
