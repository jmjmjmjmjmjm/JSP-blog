package com.cos.blog.config;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DB {
	public static Connection getConnection() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/TestDB");
			Connection conn = ds.getConnection();
			System.out.println("디비 연결됨");
			return conn;
		} catch (Exception e) {
			System.out.println("DB연결안됨");
		}

		return null;
	}
}
