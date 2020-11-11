package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.AdminMenu;

/**
 * 
 * @author mgonchar
 *
 */
public class AdminDao {
	Connection connection;
	ResultSet admin = null;

	/**
	 * 
	 * @param connection
	 */
	public AdminDao(Connection connection) {
		this.connection = connection;
	}

	public void login() {  // admin login
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your admin name");
		String adminname = sc.nextLine();
		System.out.println("Please enter your password");
		String password = sc.nextLine();
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("select * from admins where adminname = ? and password = ?");
			pStatement.setString(1, adminname);
			pStatement.setString(2, password);
			admin = pStatement.executeQuery();
//			System.out.println("admin: "+admin);
			if (admin.next()) {
				AdminMenu login = new AdminMenu();
				String loginAdmin = admin.getString("adminname");
				login.setResultset(loginAdmin);
			} else {
				System.out.print("Access denied.");
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}
}
