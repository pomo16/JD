package com.ssm.maintain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.common.entity.Product;
import com.ssm.maintain.dao.IProductDao;

@Service("productBo")
public class ProductBo implements IProductBo {
	
	@Autowired
	private IProductDao productDao;

	@Override
	public void addProduct(Product product) {
		productDao.insert(product);
	}

	@Override
	public Map<String, Object> productList(Product product) {
		PageHelper.startPage(product.getPage(), product.getRows());
		PageHelper.orderBy("id asc");
		List<Product> allProducts = productDao.getAllProduct();
		PageInfo page = new PageInfo(allProducts, product.getRows());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", productDao.countProduct());
		map.put("rows", page.getList());
		return map;
	}

	@Override
	public boolean productCodeValidate(String productCode) {
		if (productDao.queryByProductCode(productCode) == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void updateById(Product product) {
		productDao.updateById(product);
	}

	@Override
	public int deleteByIds(int[] ids) {
		return productDao.deleteByIds(ids);
	}

}
