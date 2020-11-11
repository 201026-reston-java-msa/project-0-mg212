package com.revature.models;

/**
 * 
 * @author mgonchar
 *
 */
public class Account {
	private int account;
	private int balance;
	private boolean approve;

	/**
	 * 
	 * @return
	 */
	public int getAccount() {
		return account;
	}

	/**
	 * 
	 * @param account
	 */
	public void setAccount(int account) {
		this.account = account;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public boolean isApprove() {
		return approve;
	}

	public void setApprove(boolean approve) {
		this.approve = approve;
	}

	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param account
	 * @param balance
	 * @param approve
	 */
	public Account(int account, int balance, boolean approve) {
		super();
		this.account = account;
		this.balance = balance;
		this.approve = approve;
	}

	@Override
	public String toString() {
		return "[" + "account=" + account + ", balance=" + balance + ", approve=" + approve + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + account;
		result = prime * result + (approve ? 1231 : 1237);
		result = prime * result + balance;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (account != other.account)
			return false;
		if (approve != other.approve)
			return false;
		if (balance != other.balance)
			return false;
		return true;
	}
	
}
