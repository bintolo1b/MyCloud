package model.bo;

import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import model.bean.User;
import model.dao.UserDAOImp;

public class UserBO {
	private static UserBO instance;
	
	private UserBO() {
	}
	
	public static UserBO getInstance() {
		if (instance==null)
			instance = new UserBO();
		return instance;
	}
	
	public boolean doesUserExits(String account) {
		ArrayList<User> userArrayList = UserDAOImp.getInstance().getAll();
		for (User user:userArrayList) {
			if (user.getUsername().equals(account))
				return true;
		}
		return false;
	}
	
	public boolean doesPasswordCorrect(String account, String password) {
		if (!doesUserExits(account))
			return false;
		ArrayList<User> userArrayList = UserDAOImp.getInstance().getAll();
		for (User user:userArrayList) {
			if (user.getUsername().equals(account)) {
				if (BCrypt.checkpw(password, user.getHashedPassword()))
					return true;
				else
					return false;
			}
		}
		return false;
	}
	
	public String CheckLogupNewUser(String username, String password, String verifyPassword, String FullName) {
		String returnMessage = "Logup successfully!";
		if (username.equals("") || password.equals("") || verifyPassword.equals("") || FullName.equals("")) {
			returnMessage = "Lack of information!";
		}
		else if (UserBO.instance.doesUserExits(username)) {
			returnMessage = "Username exists!";
		}
		else if (!UserBO.instance.doesUserExits(username)) {
			if (!password.equals(verifyPassword)) {
				returnMessage = "Verify password is incorrect!";
			}
		}
		return returnMessage;
	}
	
	public String CheckLogin(String username, String password) {
		String message = "";
		if (UserBO.getInstance().doesUserExits(username)) {
			if (!UserBO.getInstance().doesPasswordCorrect(username, password))
				message = "Password incorrect!";
			else {
				message = "Login successfully!";
		    }
		}
		else {
			message = "Account doesn't exist!";
		}
		return message;
	}
	
	public void addNewUser(String username, String password, String fullName) {
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		User newUser = new User(username, hashedPassword, fullName);
		UserDAOImp.getInstance().Insert(newUser);
	}
	
	public User getUser(String username) {
		ArrayList<User> users = UserDAOImp.getInstance().getAll();
		for (User user:users) {
			if (user.getUsername().equals(username))
				return user;
		}
		return null;
	}
}
