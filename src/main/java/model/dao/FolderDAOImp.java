package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Folder;

public class FolderDAOImp implements DAOInterface<Folder> {	
	private static FolderDAOImp instance;
	private Connection connect = ConnectDatabase.getConnection();
	private FolderDAOImp() {
	}
	public static FolderDAOImp getInstance() {
		if (instance == null)
			instance = new FolderDAOImp();
		return instance;
	}
	@Override
	public void Insert(Folder obj) {
		String query = "insert into folder(ownerUsername, parentFolderId, name, path) value"
				+ "(?,?,?,?)";
		try {
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setObject(1, obj.getOwnerUsername());
			pst.setObject(2, obj.getParentFolderId());
			pst.setObject(3, obj.getName());
			pst.setObject(4, obj.getPath());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Update(Folder obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(Folder obj) {
		String query = "delete from folder where id = ?";
		try {
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setObject(1, obj.getId());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Folder> getAll() {
		ArrayList<Folder> folderArrayList = new ArrayList<Folder>();
		try {
			String sqlQuery = "SELECT* FROM folder";
			PreparedStatement pst = connect.prepareStatement(sqlQuery);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				Folder folder = new Folder(res.getInt(1), res.getString(2), res.getInt(3), res.getString(4), res.getString(5));
				folderArrayList.add(folder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return folderArrayList;
	}

	@Override
	public ArrayList<Folder> selectByCondition(String condition, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

}
