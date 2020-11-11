package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.Main;

/**
 * 
 * @author mgonchar
 *
 */
public class ConnectionUtil {
	
	final Logger logger = Logger.getLogger(Main.class);
	
	private Connection connection;
	String url = "jdbc:postgresql://localhost:5432/bankofmax?currentSchema=public";
	String username = "postgres";
	String password = "postgres";

//	String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=chinook";
//	String username = "postgres";
//	String password = "postgres";

	public ConnectionUtil() {
		try {
			this.connection = DriverManager.getConnection(this.url, this.username, this.password);
			logger.info("Connection to the database established");
			
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	/**
	 * 
	 * @return
	 */
	public Connection getConnection() {
		return connection;
	}

	public void close() {
		try {
			this.connection.close();
		} catch (SQLException e) {

		}
	}
}
