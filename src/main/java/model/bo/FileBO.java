package model.bo;

import java.util.ArrayList;

import model.bean.File;
import model.bean.Folder;
import model.dao.FileDAOImp;

public class FileBO {
	private static FileBO instance;
	private FileBO() {
	}
	
	public static FileBO getInstance() {
		if (instance==null)
			instance = new FileBO();
		return instance;
	}
	
	public ArrayList<File> getAllFilesOfFolder(Folder folder){
		ArrayList<File> fileArrayList = FileDAOImp.getInstance().getAll();
		ArrayList<File> filesOfFolder = new ArrayList<File>();
		for (File file:fileArrayList) {
			if (file.getFolderId()==folder.getId())
				filesOfFolder.add(file);
		}
		return filesOfFolder;
	}
}
