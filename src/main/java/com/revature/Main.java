package com.revature;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.AdminDao;
import com.revature.dao.UserDao;
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
public class Main {
	public static void main(String[] args) {

		final Logger logger = Logger.getLogger(Main.class);
		logger.info("Welcome Menu - Bank of Max");

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nBANK OF MAX. Please make your selection.\n");
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("1. Login.");
		System.out.println("2. Register.");
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("1");
		commands.add("2");
		commands.add("3");
		Scanner sc = new Scanner(System.in);
		String command = sc.nextLine();
		if (commands.contains(command)) {
			if (command.contentEquals("1")) { // user login
				ConnectionUtil cu = new ConnectionUtil();
				UserDao start = new UserDao(cu.getConnection());
				start.getOne();
				cu.close();
				Main restart = new Main();
				String[] arguments = new String[] {};
				restart.main(arguments);
			}
			if (command.contentEquals("2")) { // register
				ConnectionUtil cu = new ConnectionUtil();
				UserDao start = new UserDao(cu.getConnection());
				start.insert();
				cu.close();
				Main restart = new Main();
				String[] arguments = new String[] {};
				restart.main(arguments);
			}
			if (command.contentEquals("3")) { // admin login
				System.out.println("\nAdmin login function. Please login.");
				ConnectionUtil cu = new ConnectionUtil();
				AdminDao start = new AdminDao(cu.getConnection());
				start.login();
				logger.info("Admin login function.");
				cu.close();
				Main restart = new Main();
				String[] arguments = new String[] {};
				restart.main(arguments);
			}
		} else {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
//			System.out.println(new String(new char[7]).replace("\0", "\r\n"));
			logger.error("Access denied.");
			System.out.println("\nInvalid entry. Please try again.\n");
			Main restart = new Main();
			String[] arguments = new String[] {};
			restart.main(arguments);
		}
	}

}
