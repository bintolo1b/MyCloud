package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Notification;

public class NotificationDAOImp implements DAOInterface<Notification> {
	private Connection connect = ConnectDatabase.getConnection();
    private static NotificationDAOImp instance;

	private NotificationDAOImp() {
	}
	
	public static NotificationDAOImp getInstance() {
		if (instance == null)
			instance = new NotificationDAOImp();
		return instance;
	}
	
	@Override
	public void Insert(Notification obj) {
		String query = "insert into notification(ownerUsername, content, time, status, sentUsername, accessLink) value(?,?,?,?,?,?)";
		try {
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setObject(1, obj.getOwnerUsername());
			pst.setObject(2, obj.getContent());
			pst.setObject(3, obj.getTime());
			pst.setObject(4, obj.getStatus());
			pst.setObject(5, obj.getSentUsername());
			pst.setObject(6, obj.getAccessLink());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void Update(Notification obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(Notification obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Notification> getAll() {
		return selectByCondition("select* from notification order by time desc");
	}

	@Override
	public ArrayList<Notification> selectByCondition(String condition, Object... params) {
		ArrayList<Notification> notifications = new ArrayList<Notification>();
	    try {	     
	        PreparedStatement pst = connect.prepareStatement(condition);
	        
	        if (params!=null)
		        for (int i = 0; i < params.length; i++) {
		            pst.setObject(i + 1, params[i]);
		        }
	        
	        ResultSet res = pst.executeQuery();
	        while (res.next()) {
	        	Notification notification = new Notification(res.getInt(1), res.getString(2), res.getString(3), res.getTimestamp(4).toLocalDateTime(), res.getString(5), res.getString(6), res.getString(7));
	        	notifications.add(notification);
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return notifications;
	}

}
