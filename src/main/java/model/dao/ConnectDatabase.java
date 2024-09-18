package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
	public static Connection getConnection() {
		Connection c = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			String url = "jdbc:mysql://localhost:3306/mycloud?autoReconnect=true&useSSL=false";
			String username = "root";
			String password = "bulletsilver";
			c = DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}
}
