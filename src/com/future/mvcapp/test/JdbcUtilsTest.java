package com.future.mvcapp.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.future.mvcapp.db.JdbcUtils;

public class JdbcUtilsTest {

	@Test
	public void testGetConnection() throws SQLException {
		Connection connection =JdbcUtils.getConnection();
		System.out.println(connection);
	}

}
