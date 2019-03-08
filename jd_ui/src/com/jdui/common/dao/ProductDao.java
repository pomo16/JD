package com.jdui.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdui.common.JDBCUtil;
import com.jdui.common.dto.ProductDto;

public class ProductDao {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/**
	 * 分页查询商品数据
	 * @return
	 */
	public List<ProductDto> queryPageProduct(int pageStart, int rows) {
		conn = JDBCUtil.getConnection();
		List<ProductDto> productList = new ArrayList<ProductDto>();
		try {
			String sql = "select * from jd.product order by id desc limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pageStart);
			ps.setInt(2, rows);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProductDto productDto = new ProductDto();
				productDto.setId(rs.getInt("id"));
				productDto.setProductCode(rs.getString("product_code"));
				productDto.setProductName(rs.getString("product_name"));
				productDto.setPrice(rs.getFloat("price"));
				productDto.setWeight(rs.getFloat("weight"));
				productDto.setPlace(rs.getString("place"));
				productDto.setDescription(rs.getString("description"));
				productDto.setPicture(rs.getString("picture"));
				productList.add(productDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, rs);
		}
		return productList;
	}
	
	/**
	 * 根据ID查询商品数据
	 * @param id
	 */
	public ProductDto queryProductById(int id) {
		conn = JDBCUtil.getConnection();
		ProductDto productDto = null;
		try {
			String sql = "select * from jd.product where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				productDto = new ProductDto();
				productDto.setId(rs.getInt("id"));
				productDto.setProductCode(rs.getString("product_code"));
				productDto.setProductName(rs.getString("product_name"));
				productDto.setPrice(rs.getFloat("price"));
				productDto.setWeight(rs.getFloat("weight"));
				productDto.setPlace(rs.getString("place"));
				productDto.setDescription(rs.getString("description"));
				productDto.setPicture(rs.getString("picture"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, rs);
		}
		return productDto;
	}
	
	/**
	 * 统计总商品数
	 * @return
	 */
	public int countProduct() {
		conn = JDBCUtil.getConnection();
		int totalProduct = 0;
		try {
			String sql = "select count(*) as totalProduct from jd.product";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				totalProduct = rs.getInt("totalProduct");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, rs);
		}
		return totalProduct;
	}
	
	/**
	 * 保存商品信息
	 * @param productDto
	 */
	public void saveProduct(ProductDto productDto) {
		conn = JDBCUtil.getConnection();
		try {
			String sql = "insert into jd.product(product_code,product_name,price,weight,place,description,picture) values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, productDto.getProductCode());
			ps.setString(2, productDto.getProductName());
			ps.setFloat(3, productDto.getPrice());
			ps.setFloat(4, productDto.getWeight());
			ps.setString(5, productDto.getPlace());
			ps.setString(6, productDto.getDescription());
			ps.setString(7, productDto.getPicture());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, null);
		}
	}
	
	/**
	 * 修改商品信息
	 * @param productDto
	 */
	public void updateProduct(ProductDto productDto) {
		conn = JDBCUtil.getConnection();
		try {
			String sql = "update jd.product set product_name = ?, price = ?, weight = ?, place = ?, description = ?, picture = ? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, productDto.getProductName());
			ps.setFloat(2, productDto.getPrice());
			ps.setFloat(3, productDto.getWeight());
			ps.setString(4, productDto.getPlace());
			ps.setString(5, productDto.getDescription());
			ps.setString(6, productDto.getPicture());
			ps.setInt(7, productDto.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, null);
		}
	}
	
	/**
	 * 批量删除商品信息
	 * @param ids
	 * @return count
	 */
	public int deleteProductByIds(String[] ids) {
		conn = JDBCUtil.getConnection();
		int count = 0;
		try {
			StringBuffer sql = new StringBuffer("delete from jd.product where id in(");
			for(int i=0;i<ids.length-1;i++) {
				sql.append("?,");
			}
			sql.append("?)");
			ps = conn.prepareStatement(sql.toString());
			for(int i=0;i<ids.length;i++) {
				ps.setInt((i+1), Integer.parseInt(ids[i]));
			}
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, null);
		}
		return count;
	}
	
	/**
	 * 根据ids查询商品信息
	 * @param ids
	 * @return pictures
	 */
	public String[] queryProductByIds(String[] ids) {
		conn = JDBCUtil.getConnection();
		String[] pictures = new String[ids.length];
		try {
			StringBuffer sql = new StringBuffer("select picture from jd.product where id in(");
			for(int i=0;i<ids.length-1;i++) {
				sql.append("?,");
			}
			sql.append("?)");
			ps = conn.prepareStatement(sql.toString());
			for(int i=0;i<ids.length;i++) {
				ps.setInt((i+1), Integer.parseInt(ids[i]));
			}
			rs = ps.executeQuery();
			int index = 0;
			while(rs.next()) {
				pictures[index] = rs.getString("picture");
				index++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, null);
		}
		return pictures;
	}
	
	/**
	 * 根据productCode查询信息
	 * @param productCode
	 * @return
	 */
	public ProductDto queryByProductCode(String productCode) {
		conn = JDBCUtil.getConnection();
		ProductDto productDto = null;
		try {
			String sql = "select * from jd.product where product_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				productDto = new ProductDto();
				productDto.setId(rs.getInt("id"));
				productDto.setProductCode(rs.getString("product_code"));
				productDto.setProductName(rs.getString("product_name"));
				productDto.setPrice(rs.getFloat("price"));
				productDto.setWeight(rs.getFloat("weight"));
				productDto.setPlace(rs.getString("place"));
				productDto.setDescription(rs.getString("description"));
				productDto.setPicture(rs.getString("picture"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, rs);
		}
		return productDto;
	}
}
