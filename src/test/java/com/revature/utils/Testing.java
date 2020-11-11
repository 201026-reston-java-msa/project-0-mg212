package com.revature.utils;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import com.revature.dao.AdminDao;
import com.revature.dao.UserDao;

public class Testing {

//	testing SQLException if DB login details are invalid
	@Test(expected = SQLException.class)
	public void establishedDbConnection() throws SQLException {
		Connection connection = DriverManager.getConnection("1", "1", "1");
	}

//	testing whether admin login info valid or not
	@Test
	public void whenGetAdminName() {
		final AdminDao adminname = new AdminDao(null);
		assertNotNull(adminname);
	}

//	testing whether user login info valid or not
	@Test
	public void whenGetUserName() {
		final UserDao username = new UserDao(null);
		assertNotNull(username);
	}

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

}
