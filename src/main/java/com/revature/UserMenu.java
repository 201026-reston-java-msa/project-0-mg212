package com.revature;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.dao.TransactionDao;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

//Rank	Log Level	Description
//1		OFF			Turn off the log
//2		FATAL		Severe errors that cause premature termination. Expect these to be immediately visible on a status console
//3		ERROR		Other runtime errors or unexpected conditions. Expect these to be immediately visible on a status console.
//4		WARN		Use of deprecated APIs, poor use of API, ‘almost’ errors, other runtime situations that are undesirable or unexpected, but not necessarily “wrong”. Expect these to be immediately visible on a status console
//5		INFO		Interesting runtime events (startup/shutdown). Expect these to be immediately visible on a console, so be conservative and keep to a minimum.
//6		DEBUG		Detailed information on the flow through the system. Expect these to be written to logs only.
//7		TRACE		Most detailed information. Expect these to be written to logs only. Since version 1.2.12

//		final Logger logger = Logger.getLogger(Main.class);
//		logger.info("Welcome Menu - Bank of Max");

/**
 * 
 * @author mgonchar
 *
 */
public class UserMenu {

	Connection connection;
	User loginUser;
	Main app;
	
	final Logger logger = Logger.getLogger(Main.class);


	/**
	 * 
	 * @param loginUser
	 * @throws SQLException
	 */
	public void setResultset(User loginUser) throws SQLException {
		this.menu(loginUser);
	}

	/**
	 * 
	 * @param user
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	private void menu(User user) throws SQLException {
		if (user != null) {

//			System.out.println(new String(new char[50]).replace("\0", "\r\n"));

			System.out.println("\nBANK OF MAX");
			System.out.println("\nWelcome back, " + user.getFirstname() + "!");
			System.out.println("Please make your selection:\n");
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("0. Open account.");
			System.out.println("1. Deposit funds.");
			System.out.println("2. Withdraw funds.");
			System.out.println("3. Transfer funds.");
			System.out.println("4. Transactions.");
			System.out.println("5. Logout.");

			Scanner sc = new Scanner(System.in);
			String command = sc.nextLine();

			if (command.contentEquals("0")) { // open an account
				ConnectionUtil cu = new ConnectionUtil();
				AccountDao account = new AccountDao(cu.getConnection());
				account.apply();
				cu.close();
				this.setResultset(user);
			}

			if (command.contentEquals("1")) { // deposit funds
				ConnectionUtil cu = new ConnectionUtil();
				AccountDao account = new AccountDao(cu.getConnection());
				System.out.print(account.getAccountByUser(user.getId()));
				System.out.println("\nEnter the account to deposit into");
				int daccount = sc.nextInt();
				System.out.println("Enter the amount to deposit");
				int damount = sc.nextInt();
				account.deposit(daccount, damount);
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(damount + " succussfully deposited into the account " + daccount + ".");
				System.out.print(account.getAccountByUser(user.getId()));
				cu.close();
				this.setResultset(user);
			}
			if (command.contentEquals("2")) { // withdraw funds
				ConnectionUtil cu = new ConnectionUtil();
				AccountDao account = new AccountDao(cu.getConnection());
				System.out.print(account.getAccountByUser(user.getId()));
				System.out.println("\nEnter the account to withdraw from");
				int waccount = sc.nextInt();
				System.out.println("Enter the amount to withdraw");
				int wamount = sc.nextInt();
				account.withdraw(waccount, wamount);
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(wamount + " successfully withdrawn from " + waccount + ".");
				System.out.print(account.getAccountByUser(user.getId()));
				cu.close();
				this.setResultset(user);
			}
			if (command.contentEquals("3")) { // transfer
				ConnectionUtil cu = new ConnectionUtil();
				AccountDao account = new AccountDao(cu.getConnection());
				System.out.print(account.getAccountByUser(user.getId()));
				System.out.println("\nEnter the account to transfer from");
				int uaccount = sc.nextInt();
				System.out.println("Enter the account to transfer to");
				int taccount = sc.nextInt();
				System.out.println("Enter the amount to transfer");
				int amount = sc.nextInt();
				account.transfer(uaccount, amount, taccount);
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(amount + " successfully transferred to the account " + taccount + ".");
//				System.out.print(account.getAccountByUser(user.getId()));
				cu.close();
				this.setResultset(user);
			}
			if (command.contentEquals("4")) { // review transactions
				ConnectionUtil cu = new ConnectionUtil();
				AccountDao account = new AccountDao(cu.getConnection());
				System.out.print(account.getAccountByUser(user.getId()));
				TransactionDao transaction = new TransactionDao(cu.getConnection());
				System.out.println("\nEnter account to review transaction history ");
				int acc = sc.nextInt();
				
				try {
					Thread.sleep(4000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.print(transaction.getTransactionByAccount(acc)); // TransactionDao call
				cu.close();
				this.setResultset(user);
			}
			if (command.contentEquals("5")) { // logout to Main
				user = null;
				System.out.println("Thank you for choosing Bank of Max!");
				Main restart = new Main();
				String[] arguments = new String[] {};
				restart.main(arguments);
			}
		} else {
			logger.error("Access denied.");
			System.out.println("\nAccess denied. Please try again.\n\n");
		}
	}
}
