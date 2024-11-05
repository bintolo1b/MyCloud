package model.bo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.servlet.http.Part;
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
			if ((int)file.getFolderId()==(int)folder.getId())
				filesOfFolder.add(file);
		}
		return filesOfFolder;
	}
	
	public File getFileByPath(String path) {
		ArrayList<File> fileArrayList = FileDAOImp.getInstance().getAll();
		for (File file:fileArrayList)
			if (file.getPath().equals(path))
				return file;
		return null;
	}
	
	public void deleteFileOnServer(String path) {
		java.io.File file = new java.io.File(path);
		if (file.exists())
			file.delete();
	}
	
	public void deleteFileOnDatabase(File file) {
		FileDAOImp.getInstance().Delete(file);
	}
	
	public ArrayList<String> saveUploadedFilesOnServer(String folderPath, Collection<Part> parts) {
		ArrayList<String> fileNames = new ArrayList<String>();
		for (Part part : parts) {
            if (part.getSubmittedFileName() != null) {
                String fileName = part.getSubmittedFileName();
                java.io.File file = new java.io.File(folderPath + java.io.File.separator + fileName);
                
                int count = 1;
                String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
                String extension = fileName.substring(fileName.lastIndexOf('.'));
                while (file.exists()) {
                    fileName = baseName + "(" + count + ")" + extension;
                    file = new java.io.File(folderPath + java.io.File.separator + fileName);
                    count++;
                }
                
                fileNames.add(fileName);

                InputStream input = null;
                OutputStream output = null;
                try {
                    input = part.getInputStream();
                    output = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                    
                } catch (IOException e) {
                    e.printStackTrace();
                    fileNames.remove(fileNames.size()-1);
                    if (file.exists()) {
                        file.delete();
                    }
                } finally {
                    try {
                        if (input != null) input.close();
                        if (output != null) output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
		return fileNames;
	}
	
	public void saveUploadedFilesOnDatabase(String folderPath, ArrayList<String> fileNames) {
		Folder folder = FolderBO.getInstance().getFolderByPath(folderPath);
		String ownerUsername = folder.getOwnerUsername();
		int folderId = folder.getId();
		
		for (String fileName:fileNames) {
				java.io.File fileOnServer = new java.io.File(folderPath+java.io.File.separator+fileName);
				String filePath = folderPath + java.io.File.separator + fileName;
				long size = fileOnServer.length();
				LocalDateTime uploadDatetime = LocalDateTime.now();
				
				File newFile = new File(null, ownerUsername, folderId, fileName, filePath, size, uploadDatetime);
				FileDAOImp.getInstance().Insert(newFile);
		}
	}

	public String changeFileName(String folderPath, String oldFileName, String newFileName, String username) {
		String returnMessage = "";
		File oldFileOnDataBase = getFileByPath(folderPath + java.io.File.separator + oldFileName);
		if (oldFileOnDataBase == null) 
			returnMessage = "File doesn't exists!";
		else if (oldFileOnDataBase != null) {
			if (!oldFileOnDataBase.getOwnerUsername().equals(username))
				returnMessage = "You have no permission to access this file!";
			else if (oldFileOnDataBase.getOwnerUsername().equals(username)) {
				if (oldFileOnDataBase.getName().equals(newFileName))
					returnMessage = "New name is the same with old one!";
				else {
					java.io.File oldFileOnServer = new java.io.File(folderPath + java.io.File.separator + oldFileName);
					java.io.File newFileOnServer = new java.io.File(folderPath + java.io.File.separator + newFileName);
					if (newFileOnServer.exists()) {
						returnMessage = "File name exists!";
					}
					else if (oldFileOnServer.renameTo(newFileOnServer)) {						
						oldFileOnDataBase.setName(newFileName);
						oldFileOnDataBase.setPath(newFileOnServer.getPath());
						FileDAOImp.getInstance().Update(oldFileOnDataBase);
						returnMessage = "Rename successfully!";
					}
					else {
						returnMessage = "Rename failed!";
					}
				}
			}
		}
		return returnMessage;
	}
	
	public ArrayList<File> searchFiles(String username, String searchContent) {
		ArrayList<File> fileArrayList = FileDAOImp.getInstance().getAll();
		ArrayList<File> searchResult = new ArrayList<File>();
		for (File file : fileArrayList) {
			if (file.getOwnerUsername().equals(username) && file.getName().toLowerCase().contains(searchContent.toLowerCase()))
				searchResult.add(file);
		}
		return searchResult;
	}
}
