package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.File;

public class FileDAOImp implements DAOInterface<File> {
	private static FileDAOImp instance;
	private Connection connect = ConnectDatabase.getConnection();
	private FileDAOImp() {
	}
	public static FileDAOImp getInstance() {
		if (instance == null)
			instance = new FileDAOImp();
		return instance;
	}
	@Override
	public void Insert(File obj) {
		String query = "insert into file(ownerUsername, folderID, name, path, size, uploadDate) value"
				+ "(?,?,?,?,?,?)";
		try {
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setObject(1, obj.getOwnerUsername());
			pst.setObject(2, obj.getFolderId());
			pst.setObject(3, obj.getName());
			pst.setObject(4, obj.getPath());
			pst.setObject(5, obj.getSize());
			pst.setObject(6, obj.getUploadDate());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void Update(File obj) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void Delete(File obj) {
		String query = "delete from file where id = ?";
		try {
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setObject(1, obj.getId());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public ArrayList<File> getAll() {
		ArrayList<File> fileArrayList = new ArrayList<File>();
		try {
			String sqlQuery = "SELECT* FROM file";
			PreparedStatement pst = connect.prepareStatement(sqlQuery);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				File file = new File(res.getInt(1), res.getString(2), res.getInt(3), res.getString(4), res.getString(5), res.getLong(6), res.getTimestamp(7).toLocalDateTime());
				fileArrayList.add(file);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fileArrayList;
	}
	@Override
	public ArrayList<File> selectByCondition(String condition, Object... params) {
		return null;
	}
	
}
