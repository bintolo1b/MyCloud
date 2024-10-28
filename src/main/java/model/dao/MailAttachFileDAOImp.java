package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.MailAttachFile;

public class MailAttachFileDAOImp implements DAOInterface<MailAttachFile> {
	private Connection connect = ConnectDatabase.getConnection();
	private static MailAttachFileDAOImp instance;
	private MailAttachFileDAOImp() {
	}
	
	public static MailAttachFileDAOImp getInstance() {
		if (instance == null)
			instance = new MailAttachFileDAOImp();
		return instance;
	}
	
	@Override
	public void Insert(MailAttachFile obj) {
		String query = "insert into mailattachfile(mailId, path, name) value"
				+ "(?,?,?)";
		try {
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setObject(1, obj.getMailId());
			pst.setObject(2, obj.getPath());
			pst.setObject(3, obj.getName());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Update(MailAttachFile obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(MailAttachFile obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<MailAttachFile> getAll() {
		return selectByCondition("select* from mailAttachFile");
	}

	@Override
	public ArrayList<MailAttachFile> selectByCondition(String condition, Object... params) {
		ArrayList<MailAttachFile> mailAttachFiles = new ArrayList<MailAttachFile>();
	    try {	     
	        PreparedStatement pst = connect.prepareStatement(condition);
	        
	        if (params!=null)
		        for (int i = 0; i < params.length; i++) {
		            pst.setObject(i + 1, params[i]);
		        }
	        
	        ResultSet res = pst.executeQuery();
	        while (res.next()) {
	        	MailAttachFile mailAttachFile = new MailAttachFile(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4));
	        	mailAttachFiles.add(mailAttachFile);
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return mailAttachFiles;
	}

}
