package model.bean;

public class User {
	private String username;
	private String hashedPassword;
	private String fullName;
	private String role;
	private long maxCapacity;
	
	public User(String username, String hashedPassword, String fullName, String role, long maxCapacity) {
		super();
		this.username = username;
		this.hashedPassword = hashedPassword;
		this.fullName = fullName;
		this.role = role;
		this.maxCapacity = maxCapacity;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public long getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(long maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	
	
}
