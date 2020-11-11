package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.UserMenu;
import com.revature.models.User;

/**
 * @author mgonchar
 *
 * 
 */
public class UserDao {
	Connection connection;
	User user;

	/**
	 * 
	 * @param connection
	 */
	public UserDao(Connection connection) {
		this.connection = connection;
	}

	public void insert() { // register new user service
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("\nWelcome to Bank of Max new user service!");
		System.out.println("Please enter username");
		String name = sc.nextLine();
		System.out.println("Please enter password");
		String pw = sc.nextLine();
		System.out.println("Please enter first name");
		String firstname = sc.nextLine();
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("insert into users(username, password, firstname) values(?,?,?)");
			pStatement.setString(1, name);
			pStatement.setString(2, pw);
			pStatement.setString(3, firstname);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			e.getMessage();
		} finally {
			System.out.println("Thank you for choosing Bank of Max, "+firstname+"! Please login.\n");
		}
	}

	public void getOne() { // user login
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter username");
		String username = sc.nextLine();
		System.out.println("Please enter password");
		String password = sc.nextLine();
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("select * from users where username = ? and password = ?");
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			ResultSet rs = pStatement.executeQuery();
			if (rs.next()) {
				String uname = rs.getString("username");
				String fname = rs.getString("firstname");
				int id = rs.getInt("id");
				User user = new User(uname, fname, id);
				UserMenu login = new UserMenu();
				login.setResultset(user);
			} else {
				System.out.print("Access denied. Please try again. \n");
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	public int getUseridByUsername(String username) {
		try {
			PreparedStatement pStatement = connection.prepareStatement("select * from users where username = ?");
			pStatement.setString(1, username);
			ResultSet rs = pStatement.executeQuery();
			if (rs.next()) {
				int tuserid = rs.getInt("id");
				return tuserid;
			} else {
				System.out.print("Access denied.");
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return 0;
	}
}
