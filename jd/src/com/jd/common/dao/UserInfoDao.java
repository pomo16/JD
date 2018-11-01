package com.jd.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jd.common.JDBCUtil;
import com.jd.common.dto.UserInfoDto;

public class UserInfoDao {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	/**
	 * �����û�����ѯ��Ϣ
	 * @param userName
	 * @return
	 */
	public UserInfoDto queryByUserName(String userName) {
		conn = JDBCUtil.getConnection();
		UserInfoDto userInfoDto = null;
		try {
			String sql = "select * from jd.user_info where user_name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			if (rs.next()) {
				userInfoDto = new UserInfoDto();
				userInfoDto.setId(rs.getInt("id"));
				userInfoDto.setUserName(rs.getString("user_name"));
				userInfoDto.setPassword(rs.getBytes("password"));
				userInfoDto.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, rs);
		}
		return userInfoDto;
	}
	
	
	/**
	 * ��ѯ�����û���Ϣ
	 * @return
	 */
	public List<UserInfoDto> queryAllUser() {
		conn = JDBCUtil.getConnection();
		List<UserInfoDto> userList = new ArrayList<UserInfoDto>();
		try {
			String sql = "select * from jd.user_info";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserInfoDto userInfoDto = new UserInfoDto();
				userInfoDto.setId(rs.getInt("id"));
				userInfoDto.setUserName(rs.getString("user_name"));
				userInfoDto.setPassword(rs.getBytes("password"));
				userInfoDto.setPhone(rs.getString("phone"));
				userList.add(userInfoDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, rs);
		}
		return userList;
	}
	
//	public boolean queryUserInfo(String userName, String password) {
//		conn = JDBCUtil.getConnection();
//		boolean flag = false;
//		try {
//			String sql = "select * from jd.user_info where user_name=? and password=?";
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, userName);
//			ps.setString(2, password);
//			rs = ps.executeQuery();
//			if (rs.next()) {
//				flag = true;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.clear(conn, ps, rs);
//		}
//		return flag;
//	}
	
	
	/**
	 * �û���Ϣ���浽���ݿ�
	 * @param userInfoDto
	 */
	public void saveUserInfo(UserInfoDto userInfoDto) {
		conn = JDBCUtil.getConnection();
		try {
			String sql = "insert into user_info(user_name,password,phone) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userInfoDto.getUserName());
			ps.setBytes(2, userInfoDto.getPassword());
			ps.setString(3, userInfoDto.getPhone());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, null);
		}
	}
	
	/**
	 * ��ҳ��ѯ�û���Ϣ
	 * @return
	 */
	public List<UserInfoDto> queryPageUser(int pageStart, int rows) {
		conn = JDBCUtil.getConnection();
		List<UserInfoDto> userList = new ArrayList<UserInfoDto>();
		try {
			String sql = "select * from jd.user_info limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pageStart);
			ps.setInt(2, rows);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserInfoDto userInfoDto = new UserInfoDto();
				userInfoDto.setId(rs.getInt("id"));
				userInfoDto.setUserName(rs.getString("user_name"));
				userInfoDto.setPassword(rs.getBytes("password"));
				userInfoDto.setPhone(rs.getString("phone"));
				userList.add(userInfoDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, rs);
		}
		return userList;
	}
	
	/**
	 * ͳ���������û���
	 * @return
	 */
	public int countUser() {
		conn = JDBCUtil.getConnection();
		int totalUser = 0;
		try {
			String sql = "select count(*) as totalUser from jd.user_info";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				//totalUser = rs.getInt("count(*)");
				totalUser = rs.getInt("totalUser");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, rs);
		}
		return totalUser;
	}
	
	public static void main(String[] args) {
		UserInfoDao userInfoDao = new UserInfoDao();
		int totalUser = userInfoDao.countUser();
		System.out.println(totalUser);
	}
}
