package com.ssm.maintain.bo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.common.entity.Product;
import com.ssm.maintain.dao.IProductDao;

@Service("productBo")
public class ProductBo implements IProductBo {
	@Resource
	private IProductDao productDao;

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		productDao.insert(product);
	}

	@Override
	public Map<String, Object> productList(Product product) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", productDao.countProduct());
		map.put("rows", productDao.queryPageProduct(product));
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
