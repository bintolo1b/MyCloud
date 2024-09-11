package model.bo;

import java.util.ArrayList;

import model.bean.Folder;
import model.dao.FolderDAOImp;

public class FolderBO {
	private static FolderBO instance;
	private FolderBO() {
	}
	
	public static FolderBO getInstance() {
		if (instance == null)
			instance = new FolderBO();
		return instance;
	}
	
	public Folder getOriginFolder(String username) {
		ArrayList<Folder> folderArrayList = FolderDAOImp.getInstance().getAll();
		for (Folder folder:folderArrayList) {
			if (folder.getOwnerUsername().equals(username) && folder.getParentFolderId()==0)
				return folder;
		}
		return null;
	}
	
	public Folder getFolderByPath(String path) {
		ArrayList<Folder> folderArrayList = FolderDAOImp.getInstance().getAll();
		for (Folder folder:folderArrayList) {
			if (folder.getPath().equals(path))
				return folder;
		}
		return null;
	}
	
	public boolean doesFolderBelongToUser(Folder validateFolder, String username) {
		ArrayList<Folder> folderArrayList = FolderDAOImp.getInstance().getAll();
		for (Folder folder:folderArrayList) {
			if (folder.getId() == validateFolder.getId()) {
				if (folder.getOwnerUsername().equals(username))
					return true;
				else
					return false;
			}
		}
		return false;
	}
	
	public ArrayList<Folder> getAllSubfolderOfFolder(Folder fatherFolder){
		ArrayList<Folder> folderArrayList = FolderDAOImp.getInstance().getAll();
		ArrayList<Folder> subFolerArrayList = new ArrayList<Folder>();
		for (Folder folder:folderArrayList) {
			if (folder.getParentFolderId()==fatherFolder.getId()) {
				subFolerArrayList.add(folder);
			}
				
		}
		return subFolerArrayList;
	}
}
