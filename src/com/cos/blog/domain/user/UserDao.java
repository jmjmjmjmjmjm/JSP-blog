package com.cos.blog.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cos.blog.config.DB;
import com.cos.blog.domain.user.dto.JoinReqDto;
import com.cos.blog.domain.user.dto.LoginReqDto;

public class UserDao {

	public User findByIdUsernameAndPassword(LoginReqDto dto) {
		String sql = "SELECT id,username,email,address FROM users where username = ? password =?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				User user = User.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.email(rs.getString("email"))
						.address(rs.getString("address"))
						.build();
				return user;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally { // 무조건 실행
			DB.close(conn, pstmt, rs);
		}
		return null;
	}

	public int findByUsername(String username) {
		String sql = "SELECT *FROM users where username = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally { // 무조건 실행
			DB.close(conn, pstmt, rs);
		}
		return -1;
	}

	public int save(JoinReqDto dto) {

		String sql = "INSERT INTO users(username, password, email, address, userRole, createDate) VALUES(?,?,?,?, 'USER', now())";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getAddress());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt, null);
		}
		return -1;

	}

	public void update() {

	}

	public void usernameCheck() {

	}

	public void findById() {

	}
}
