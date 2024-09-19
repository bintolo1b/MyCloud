package model.bo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.servlet.http.Part;
import model.bean.Folder;
import model.dao.FileDAOImp;
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
	
	public String saveUploadedFolderOnServer(String currentFolderPath, Collection<Part> colectionParts) {
		ArrayList<Part> parts = new ArrayList<Part>(colectionParts);
		
		String uploadFolderName = null;
		ArrayList<String[]> filePathsComponents = new ArrayList<String[]>();
		java.io.File uploadFolder = null;
		
		try {
			for (int i = 0 ;  i < parts.size() ; i++) {
				String fileName = parts.get(i).getSubmittedFileName();
	        	uploadFolderName = fileName.substring(0, fileName.indexOf('/'));
	        	fileName = fileName.substring(fileName.indexOf('/')+1);
	        	filePathsComponents.add(fileName.split("/")); 
			}
			
			if (uploadFolderName != null) {
				uploadFolder = new java.io.File(currentFolderPath + java.io.File.separator + uploadFolderName);
	            
	            int count = 1;
	            String originalFolderName = uploadFolderName;
	            while (uploadFolder.exists()) {
	            	uploadFolderName = originalFolderName + "(" + count + ")";
	            	uploadFolder = new java.io.File(currentFolderPath + java.io.File.separator + uploadFolderName);
	                count++;
	            }
	            uploadFolder.mkdir();
	            
	            for (int k = 0 ; k < filePathsComponents.size(); k++) {
	            	String[] filePathComponent = filePathsComponents.get(k);
	            	String currentSubfolderPath = uploadFolder.getPath();
	            	for (int i = 0 ; i < filePathComponent.length - 1; i++) {
	            		File subFolder = new File(currentSubfolderPath + File.separator + filePathComponent[i]);
	            		if (!subFolder.exists())
	            			subFolder.mkdir();
	            		currentSubfolderPath = currentSubfolderPath + File.separator + filePathComponent[i];
	            	}
	            	
	            	String uploadedFileNameOfCurrentSubFolder = filePathComponent[filePathComponent.length - 1];
	            	File uploadedFileOfCurrentSubFolder = new File(currentSubfolderPath + File.separator + uploadedFileNameOfCurrentSubFolder);
	            	InputStream input = null;
	                OutputStream output = null;
	                try {
	                	input = parts.get(k).getInputStream();
		                output = new FileOutputStream(uploadedFileOfCurrentSubFolder);
		                byte[] buffer = new byte[1024];
		                int bytesRead;
		                
		                while ((bytesRead = input.read(buffer)) != -1) {
		                    output.write(buffer, 0, bytesRead);
		                }
		                
		                input.close();
		                output.close();
	                }
	                catch (IOException ex) {
						ex.printStackTrace();
						throw new IOException("Error occurred while saving files.");
					}
	                
	                
	            }
			}
		}
		catch (Exception e) {
			uploadFolderName = null;
			deleteFolderOnServer(uploadFolder);
			e.printStackTrace();
		}
		return uploadFolderName;
	}
	
	private void deleteFolderOnServer(File folder) {
		try {
			if (folder.isDirectory()) {
	            File[] files = folder.listFiles();
	            if (files != null) {
	                for (File file : files) {
	                    if (file.isDirectory()) {
	                        deleteFolderOnServer(file);
	                    } else {
	                        file.delete();
	                    }
	                }
	            }
	        }
	        folder.delete();
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
    }
	
	public void saveUploadedFolderOnDatabase(String currentFolderPath, String uploadedFolderName) {
		Folder currentFolder = FolderBO.getInstance().getFolderByPath(currentFolderPath);
		String ownerUsername = currentFolder.getOwnerUsername();
		
		File uploadedFolder = new File(currentFolderPath + File.separator + uploadedFolderName);
		
		Folder uploadedBeanFolder = new Folder(null, ownerUsername, currentFolder.getId(), uploadedFolderName, uploadedFolder.getPath());
		FolderDAOImp.getInstance().Insert(uploadedBeanFolder);
		uploadedBeanFolder = FolderBO.getInstance().getFolderByPath(uploadedFolder.getPath());
		
		if (uploadedFolder.exists()) {
			for (File file:uploadedFolder.listFiles()) {
				if (file.isDirectory()) {
					saveUploadedFolderOnDatabase(uploadedFolder.getPath(), file.getName());
				}
				else {
					model.bean.File newFile = new model.bean.File(null, ownerUsername, uploadedBeanFolder.getId(), file.getName(), file.getPath(), file.length() , LocalDateTime.now());
					FileDAOImp.getInstance().Insert(newFile);
				}
			}	
		}
	}
	
	
}
