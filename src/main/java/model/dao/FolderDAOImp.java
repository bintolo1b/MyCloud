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
		String query = "insert into folder(ownerUsername, parentFolderId, name, path, size) value"
				+ "(?,?,?,?,?)";
		try {
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setObject(1, obj.getOwnerUsername());
			pst.setObject(2, obj.getParentFolderId());
			pst.setObject(3, obj.getName());
			pst.setObject(4, obj.getPath());
			pst.setObject(5, obj.getSize());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Update(Folder obj) {
	    String query = "UPDATE folder "
	            + "SET ownerUsername = ?, name = ?, path = ?, uploadDate = ?, size = ? "
	            + "WHERE id = ?";
	    try {
	        PreparedStatement pst = connect.prepareStatement(query);
	        pst.setObject(1, obj.getOwnerUsername());	  
	        pst.setObject(2, obj.getName());
	        pst.setObject(3, obj.getPath());
	        pst.setObject(4, obj.getUploadDate());
	        pst.setObject(5, obj.getSize());
	        pst.setObject(6, obj.getId());

	        pst.executeUpdate();
	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	        e.printStackTrace();
	    }
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
				Folder folder = new Folder(res.getInt(1), res.getString(2), res.getInt(3), res.getString(4), res.getString(5), res.getTimestamp(6).toLocalDateTime(), res.getLong(7));
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
	
	public void UpdateAllSubFoldersPathAfterRenameFolder(String folderPath, String oldFolderName, String newFolderName) {
		String oldPath = folderPath + java.io.File.separator + oldFolderName;
		String newPath = folderPath + java.io.File.separator + newFolderName;
		
		String query = "UPDATE folder SET path = CONCAT(?, SUBSTRING(path, ?)) "
		        + "WHERE path = ? OR (path LIKE ? AND SUBSTRING(path, ?, 1) = ?)";

		   try {  
		       PreparedStatement pst = connect.prepareStatement(query);
		       pst.setString(1, newPath); 
		       pst.setInt(2, oldPath.length() + 1);
		       pst.setString(3, oldPath); 
		       pst.setString(4, oldPath.replace("\\", "\\\\") + "%"); 
		       pst.setInt(5, oldPath.length() + 1);
		       pst.setString(6, java.io.File.separator);
		       pst.executeUpdate();
		       
		   } catch (SQLException e) {
		       e.printStackTrace();
		   }
	}


}
