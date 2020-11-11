package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Transaction;

/**
 * 
 * @author mgonchar
 *
 */
public class TransactionDao {
	Connection connection;

	/**
	 * 
	 * @param connection
	 */
	public TransactionDao(Connection connection) {
		super();
		this.connection = connection;
	}

	/**
	 * 
	 * @param account
	 * @return
	 */
	public List<Transaction> getTransactionByAccount(int account) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction transaction;
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"select * from transactions join accounts on transactions.accountid = accounts.id where transactions.accountid=?");
			pStatement.setInt(1, account);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				String content = resultSet.getString("context");
				transaction = new Transaction(content);
				transactions.add(transaction);
			}
		} catch (SQLException e) {

		}
		return transactions;

	}
}
