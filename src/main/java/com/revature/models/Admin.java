package com.revature.models;

/**
 * 
 * @author mgonchar
 *
 */
public class Admin {
	private String adminname;
	private String password;

	public String getAdminname() {
		return adminname;
	}

	/**
	 * 
	 * @param adminname
	 */
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [adminname=" + adminname + ", password=" + password + "]";
	}

	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public Admin(String adminname, String password) {
		this.adminname = adminname;
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adminname == null) ? 0 : adminname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		Admin other = (Admin) obj;
		if (adminname == null) {
			if (other.adminname != null)
				return false;
		} else if (!adminname.equals(other.adminname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
}
