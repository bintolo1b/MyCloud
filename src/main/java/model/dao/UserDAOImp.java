package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.User;

public class UserDAOImp implements DAOInterface<User> {
	private static UserDAOImp instance;
	private Connection connect = ConnectDatabase.getConnection();
	private UserDAOImp() {
	}
	public static UserDAOImp getInstance() {
		if (instance == null)
			instance = new UserDAOImp();
		return instance;
	}
	@Override
	public void Insert(User obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update(User obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(User obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<User> getAll() {
		ArrayList<User> userArrayList = new ArrayList<User>();
		try {
			String sqlQuery = "SELECT* FROM user";
			PreparedStatement pst = connect.prepareStatement(sqlQuery);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				User user = new User(res.getString(1), res.getString(2));
				userArrayList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userArrayList;
	}

	@Override
	public ArrayList<User> selectByCondition(String condition, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

}
