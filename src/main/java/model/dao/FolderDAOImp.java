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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update(Folder obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(Folder obj) {
		// TODO Auto-generated method stub
		
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
