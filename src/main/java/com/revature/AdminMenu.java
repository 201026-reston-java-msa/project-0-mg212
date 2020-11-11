package com.revature;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDao;
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
public class AdminMenu {
	Main app;

	final Logger logger = Logger.getLogger(Main.class);

	/**
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void setResultset(String user) throws SQLException {
		this.menu(user);
	}

	/**
	 * 
	 * @param user
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	private void menu(String user) throws SQLException {
		if (user != null) {
//			System.out.println(new String(new char[50]).replace("\0", "\r\n"));
			System.out.println("\nWelcome to the admin function, Admin!");
			System.out.println("\nPlease make your selection:\n");
			System.out.println("0. Review applications.");
			System.out.println("1. Deposit funds.");
			System.out.println("2. Withdraw funds.");
			System.out.println("3. Transfer funds.");
			System.out.println("4. All active accounts.");
			System.out.println("5. Logout.");

//			System.out.println("Please make your selection:");
//			System.out.println("0. Pending applications."); 
//			System.out.println("1. Withdraw funds.");
//			System.out.println("2. Transfer funds.");
//			System.out.println("3. Deposit funds.");
//			System.out.println("4. Show all active checking account");
//			System.out.println("5. Log Out");
			Scanner sc = new Scanner(System.in);
			int command = sc.nextInt();
			if (command == 0) { // approve pending accounts
				ConnectionUtil cu = new ConnectionUtil();
				AccountDao accountDao = new AccountDao(cu.getConnection());
				System.out.println(accountDao.getAllUnapprove());
				System.out.println("Enter account number: ");
				int approveAcc = sc.nextInt();
				accountDao.approveAccount(approveAcc);
				
				logger.info("Account " + approveAcc + " approved.");
				
				cu.close();
				this.menu(user);
			}
			if (command == 1) { // deposit funds
				ConnectionUtil cu = new ConnectionUtil();
				AccountDao account = new AccountDao(cu.getConnection());
				System.out.println(account.getAll());
				System.out.println("\nEnter the receiving account number ");
				int daccount = sc.nextInt();
				System.out.println("Enter the amount ");
				int damount = sc.nextInt();
				account.deposit(daccount, damount);
				
				logger.info(damount + " deposited into " + daccount + ".");

//				System.out.println(damount + " deposited into " + daccount + ".");
				
				cu.close();
				this.menu(user);
			}

//			System.out.println("Please make your selection:");
//			System.out.println("0. Review applications.");
//			System.out.println("1. Deposit funds.");
//			System.out.println("2. Withdraw funds.");
//			System.out.println("3. Transfer funds.");
//			System.out.println("4. All accounts.");
//			System.out.println("5. Logout");
			if (command == 2) { // withdraw funds
				ConnectionUtil cu = new ConnectionUtil();
				AccountDao account = new AccountDao(cu.getConnection());
				System.out.println(account.getAll());
				System.out.println("\nEnter the account to withdraw from");
				int waccount = sc.nextInt();
				System.out.println("Enter the amount to withdraw");
				int wamount = sc.nextInt();
				account.withdraw(waccount, wamount);
				logger.info(wamount + " withdrawn from " + waccount + ".");

//				System.out.println(wamount + " withdrawn from " + waccount + ".");
				
				cu.close();
				this.menu(user);
			}
			if (command == 3) { // transfer funds
				ConnectionUtil cu = new ConnectionUtil();
				AccountDao accountDao = new AccountDao(cu.getConnection());
				System.out.println(accountDao.getAll());
				System.out.println("\nEnter the account to transfer from");
				int uaccount = sc.nextInt();
				System.out.println("Enter the account to transfer to");
				int taccount = sc.nextInt();
				System.out.println("Enter the amount to transfer");
				int amount = sc.nextInt();
				accountDao.transfer(uaccount, amount, taccount);
				logger.info(amount + " transferred into " + taccount + ".");

//				System.out.println(amount + " transferred into " + taccount + ".");
				cu.close();
				this.menu(user);
			}
			
//			System.out.println("Please make your selection:");
//			System.out.println("0. Review applications.");
//			System.out.println("1. Deposit funds.");
//			System.out.println("2. Withdraw funds.");
//			System.out.println("3. Transfer funds.");
//			System.out.println("4. All accounts.");
//			System.out.println("5. Logout");
			if (command == 4) { // display all accounts
				ConnectionUtil cu = new ConnectionUtil();
				AccountDao accountDao = new AccountDao(cu.getConnection());
				System.out.println(accountDao.getAll());
				cu.close();
				this.menu(user);
			}
			if (command == 5) { // logout
				user = null;
				logger.info("Logout logger");
				System.out.println("Thank you for choosing Bank of Max! "
						+ "You have successfully logged out.");
				Main restart = new Main();
				String[] arguments = new String[] {};
				restart.main(arguments);
			}
		} else {
			System.out.println("Access denied.\n");
		}
	}

}
