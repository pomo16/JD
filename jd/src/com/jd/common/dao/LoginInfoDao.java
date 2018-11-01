package com.jd.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.jd.common.JDBCUtil;
import com.jd.common.dto.LoginInfoDto;

public class LoginInfoDao {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public void saveLoginInfo(LoginInfoDto loginInfoDto) {
		conn = JDBCUtil.getConnection();
		try {
			String sql = "insert into login_info(user_id,login_time,login_ip) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, loginInfoDto.getUserId());
			ps.setTimestamp(2, loginInfoDto.getLoginTime());
			ps.setString(3, loginInfoDto.getLoginIp());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, null);
		}
	}
	
	/**
	 * 查询用户所有登录信息
	 * @return
	 */
	public List<LoginInfoDto> queryAllLoginInfo(int userId) {
		conn = JDBCUtil.getConnection();
		List<LoginInfoDto> loginInfoList = new ArrayList<LoginInfoDto>();
		try {
			String sql = "select * from jd.login_info where user_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				LoginInfoDto loginInfoDto = new LoginInfoDto();
				loginInfoDto.setId(rs.getInt("id"));
				loginInfoDto.setUserId(rs.getInt("user_id"));
				Timestamp loginTime = rs.getTimestamp("login_time");
				loginInfoDto.setLoginTime(loginTime);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				loginInfoDto.setLoginTimeStr(sdf.format(loginTime));
				loginInfoDto.setLoginIp(rs.getString("login_ip"));
				loginInfoList.add(loginInfoDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, rs);
		}
		return loginInfoList;
	}
	
	/**
	 * 分页查询用户数据
	 * @return
	 */
	public List<LoginInfoDto> queryPageLoginInfo(int userId, int pageStart, int rows) {
		conn = JDBCUtil.getConnection();
		List<LoginInfoDto> loginInfoList = new ArrayList<LoginInfoDto>();
		try {
			String sql = "select * from jd.login_info where user_id = ? limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, pageStart);
			ps.setInt(3, rows);
			rs = ps.executeQuery();
			while (rs.next()) {
				LoginInfoDto loginInfoDto = new LoginInfoDto();
				loginInfoDto.setId(rs.getInt("id"));
				loginInfoDto.setUserId(rs.getInt("user_id"));
				Timestamp loginTime = rs.getTimestamp("login_time");
				loginInfoDto.setLoginTime(loginTime);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				loginInfoDto.setLoginTimeStr(sdf.format(loginTime));
				loginInfoDto.setLoginIp(rs.getString("login_ip"));
				loginInfoList.add(loginInfoDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, rs);
		}
		return loginInfoList;
	}
	/**
	 * 统计总登录信息数
	 * @return
	 */
	public int countLoginInfo(int userId) {
		conn = JDBCUtil.getConnection();
		int totalLoginInfo = 0;
		try {
			String sql = "select count(*) as totalLoginInfo from jd.login_info where user_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				totalLoginInfo = rs.getInt("totalLoginInfo");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, rs);
		}
		return totalLoginInfo;
	}
}


