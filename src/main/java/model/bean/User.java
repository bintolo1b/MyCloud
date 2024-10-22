package model.bean;

public class User {
	private String username;
	private String hashedPassword;
	private String fullName;
	
	public User(String username, String hashedPassword, String fullName) {
		super();
		this.username = username;
		this.hashedPassword = hashedPassword;
		this.fullName = fullName;
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
	
	
	
}
