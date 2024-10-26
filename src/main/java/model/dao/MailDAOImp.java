package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Mail;

public class MailDAOImp implements DAOInterface<Mail> {
	private Connection connect = ConnectDatabase.getConnection();
	private static MailDAOImp instance;
	private MailDAOImp() {
	}
	public static MailDAOImp getInstance() {
		if (instance == null)
			instance = new MailDAOImp();
		return instance;
	}
	
	@Override
	public void Insert(Mail obj) {
		String query = "insert into mail(id ,senderUsername, receiverUsername, topic, content, sentDate, status) value"
				+ "(?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setObject(1, obj.getId());
			pst.setObject(2, obj.getSenderUsername());
			pst.setObject(3, obj.getReceiverUsername());
			pst.setObject(4, obj.getTopic());
			pst.setObject(5, obj.getContent());
			pst.setObject(6, obj.getSentDate());
			pst.setObject(7, obj.getStatus());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Update(Mail obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(Mail obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Mail> getAll() {
		String query = "select* from mail order by sentDate desc";
		return selectByCondition(query);
	}

	@Override
	public ArrayList<Mail> selectByCondition(String condition, Object... params) {
		ArrayList<Mail> mailArrayList = new ArrayList<Mail>();
	    try {	     
	        PreparedStatement pst = connect.prepareStatement(condition);
	        
	        if (params!=null)
		        for (int i = 0; i < params.length; i++) {
		            pst.setObject(i + 1, params[i]);
		        }
	        
	        ResultSet res = pst.executeQuery();
	        while (res.next()) {
				Mail mail = new Mail(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getTimestamp(6).toLocalDateTime(), res.getString(7));
				mailArrayList.add(mail);
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return mailArrayList;
	}
	
	public int getLatestId() {
		int latestId = 0;
		try {	     
			String query = "select* from mail order by id desc";
	        PreparedStatement pst = connect.prepareStatement(query);
	        
	        ResultSet res = pst.executeQuery();
	        while (res.next()) {
				latestId = res.getInt(1);
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return latestId;
	}
	
}
