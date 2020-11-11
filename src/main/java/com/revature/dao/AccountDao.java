package com.revature.dao;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Timestamp;

import com.revature.models.Account;

/**
 * 
 * @author mgonchar
 *
 */
public class AccountDao {
	Connection connection;

	/**
	 * 
	 * @param connection
	 */
	public AccountDao(Connection connection) {
		this.connection = connection;
	}

	public void apply() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the initial deposit amount ");
		int initialDeposit = sc.nextInt();
		SecureRandom random = new SecureRandom();
		int num = 10000 + random.nextInt(70000);
		try {
			PreparedStatement pStatement = connection.prepareStatement("insert into accounts values(?,?,?)");
			PreparedStatement pStatement2 = connection
					.prepareStatement("insert into useraccounts(checkaccount,userid) values(?,?)");
			pStatement.setInt(1, num);
			pStatement.setInt(2, initialDeposit);
			pStatement.setBoolean(3, false);
			pStatement2.setInt(1, num);
			pStatement2.setInt(2, 1);
			pStatement.executeUpdate();
			pStatement2.executeUpdate();
			System.out.println("Thank you for applying!");
		} catch (SQLException e) {
			e.getMessage();
		} finally {
		}
	}

	public List<Account> getAllUnapprove() {
		List<Account> accounts = new ArrayList<Account>();
		Account account;
		try {
			PreparedStatement statement = connection.prepareStatement("select * from accounts where approved = false");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int acc = resultSet.getInt("id");
				int balance = resultSet.getInt("balance");
				boolean approved = resultSet.getBoolean("approved");
				account = new Account(acc, balance, approved);
				accounts.add(account);
			}
		} catch (SQLException e) {

		}
		return accounts;
	}

	/**
	 * 
	 * @return
	 */
	public List<Account> getAll() {
		List<Account> accounts = new ArrayList<Account>();
		Account account;
		try {
			PreparedStatement statement = connection.prepareStatement("select * from accounts  where approved = true");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int acc = resultSet.getInt("id");
				int balance = resultSet.getInt("balance");
				boolean approved = resultSet.getBoolean("approved");
				account = new Account(acc, balance, approved);
				accounts.add(account);
			}
		} catch (SQLException e) {

		}
		return accounts;
	}

	/**
	 * 
	 * @param account
	 */
	public void approveAccount(int account) {
		try {
			PreparedStatement pStatement = connection
					.prepareStatement("update accounts set approved = true where id = ?");
			pStatement.setInt(1, account);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	/**
	 * 
	 * @param userid
	 * @return
	 */
	public List<Account> getAccountByUser(int userid) {
		List<Account> accounts = new ArrayList<Account>();
		Account account;
		try {
			PreparedStatement statement = connection.prepareStatement(
					"select * from accounts join useraccounts on accounts.id = useraccounts.checkAccount join users on useraccounts.userid = users.id where accounts.approved = true and users.id = ?");
			statement.setInt(1, userid);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int acc = resultSet.getInt("id");
				int balance = resultSet.getInt("balance");
				boolean approved = true;
				account = new Account(acc, balance, approved);
				accounts.add(account);
			}
		} catch (SQLException e) {

		}
		return accounts;
	}

	/**
	 * 
	 * @param account
	 * @param money
	 */
	public void deposit(int account, int money) {
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			System.out.println(timestamp);

			String content = "Deposited " + money + " into account: " + account + " at " + timestamp;
			PreparedStatement pStatement = connection
					.prepareStatement("update accounts set balance = balance +? where id = ?");
			PreparedStatement pStatement2 = connection
					.prepareStatement("insert into transactions(context,accountid) values (?,?)");
			pStatement.setInt(1, money);
			pStatement.setInt(2, account);
			pStatement2.setString(1, content);
			pStatement2.setInt(2, account);
			pStatement.executeUpdate();
			pStatement2.executeUpdate();
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	/**
	 * 
	 * @param account
	 * @param money
	 */
	public void withdraw(int account, int money) {
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String content = "Withdrawn " + money + " into account: " + account + " at " + timestamp;
			PreparedStatement pStatement = connection
					.prepareStatement("update accounts set balance = balance -? where id = ?");
			PreparedStatement pStatement2 = connection
					.prepareStatement("insert into transactions(context,accountid) values (?,?)");
			pStatement.setInt(1, money);
			pStatement.setInt(2, account);
			pStatement2.setString(1, content);
			pStatement2.setInt(2, account);
			pStatement.executeUpdate();
			pStatement2.executeUpdate();
		} catch (SQLException e) {
			e.getMessage();
		}

	}

	/**
	 * 
	 * @param uaccount
	 * @param money
	 * @param taccount
	 */
	public void transfer(int uaccount, int money, int taccount) {
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String content = money + " transferred into account: " + taccount + " at " + timestamp;
			String content2 = money + " received from account: " + uaccount + " at " + timestamp;
			PreparedStatement pStatement = connection
					.prepareStatement("update accounts set balance = balance -? where id = ?");
			pStatement.setInt(1, money);
			pStatement.setInt(2, uaccount);
			PreparedStatement pStatement2 = connection
					.prepareStatement("update accounts set balance = balance +? where id = ?");
			pStatement2.setInt(1, money);
			pStatement2.setInt(2, taccount);
			PreparedStatement pStatement3 = connection
					.prepareStatement("insert into transactions(context,accountid) values (?,?)");
			PreparedStatement pStatement4 = connection
					.prepareStatement("insert into transactions(context,accountid) values (?,?)");
			pStatement3.setString(1, content);
			pStatement3.setInt(2, uaccount);
			pStatement4.setString(1, content2);
			pStatement4.setInt(2, taccount);
			pStatement.executeUpdate();
			pStatement2.executeUpdate();
			pStatement3.executeUpdate();
			pStatement4.executeUpdate();
		} catch (SQLException e) {
			e.getMessage();
		}

	}
}
