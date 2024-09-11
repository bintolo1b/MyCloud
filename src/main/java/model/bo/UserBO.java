package model.bo;

import java.util.ArrayList;

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
				if (user.getPassword().equals(password))
					return true;
				else
					return false;
			}
		}
		return false;
	}
}
