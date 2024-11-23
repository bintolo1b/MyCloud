package model.bo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import constant.Server;
import jakarta.servlet.http.Part;
import model.bean.Folder;
import model.bean.User;
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
			if (folder.getOwnerUsername().equals(username) && (int)folder.getParentFolderId()==0)
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
	
	public ArrayList<Folder> getAllSubfolderOfFolder(Folder fatherFolder){
		ArrayList<Folder> folderArrayList = FolderDAOImp.getInstance().getAll();
		ArrayList<Folder> subFolerArrayList = new ArrayList<Folder>();
		for (Folder folder:folderArrayList) {
			if ((int)folder.getParentFolderId()==(int)fatherFolder.getId()) {
				subFolerArrayList.add(folder);
			}	
		}
		return subFolerArrayList;
	}
	
	public ArrayList<model.bean.File> getAllFilesOfFolder(Folder folder){
		ArrayList<model.bean.File> fileArrayList = FileDAOImp.getInstance().getAll();
		ArrayList<model.bean.File> filesOfFolder = new ArrayList<model.bean.File>();
		for (model.bean.File file:fileArrayList)
			if ((int)file.getFolderId()==(int)folder.getId()) {
				filesOfFolder.add(file);
			}
		return filesOfFolder;
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
	        	//System.out.print(fileName +" "+ uploadFolderName);
	        	fileName = fileName.substring(fileName.indexOf('/')+1);
	        	//System.out.println(" "+fileName);
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
			
			try {
				deleteFolderOnServer(uploadFolder);
			} catch (IOException e1) { //luc nay xay ra su bat dong bo giua database va server
				e1.printStackTrace(); 
			}
			
			e.printStackTrace();
		}
		return uploadFolderName;
	}
	
	public void deleteFolderOnServer(File folder) throws IOException {
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
	
	public void saveUploadedFolderOnDatabase(String currentFolderPath, String uploadedFolderName) {
		Folder currentFolder = FolderBO.getInstance().getFolderByPath(currentFolderPath);
		String ownerUsername = currentFolder.getOwnerUsername();
		
		File uploadedFolder = new File(currentFolderPath + File.separator + uploadedFolderName);
		
		Folder uploadedBeanFolder = new Folder(null, ownerUsername, currentFolder.getId(), uploadedFolderName, uploadedFolder.getPath(), null, folderSize(uploadedFolder));
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

	public void deleteFolderOnDatabase(Folder deletedFolder) {
		ArrayList<Folder> subFolders = getAllSubfolderOfFolder(deletedFolder);
		for (Folder folder: subFolders)
			deleteFolderOnDatabase(folder);
		
		ArrayList<model.bean.File> filesOfFolder = getAllFilesOfFolder(deletedFolder);
		for (model.bean.File file:filesOfFolder) {
			FileDAOImp.getInstance().Delete(file);
		}
		
		FolderDAOImp.getInstance().Delete(deletedFolder);
	}
	
	public void createRootFolder(User user) {
		String newFolderPath = Server.USER_FOLDER_PATH+"\\"+user.getUsername();
		Folder folder = new Folder(null, user.getUsername(), null, user.getUsername(), newFolderPath, null, 0);
		createRootFolderOnServer(newFolderPath);
		insertRootFolderOnDatabase(folder);
	}
	
	public void createRootFolderOnServer(String path) {
		File newFolder = new File(path);
		if (!newFolder.exists()) {
			newFolder.mkdir();
		}
	}
	
	public void insertRootFolderOnDatabase(Folder folder) {
		FolderDAOImp.getInstance().Insert(folder);
	}
	
	public String createNewFolder(String folderPath, String folderName, String username) {
		Folder fatherFolder = getFolderByPath(folderPath);
		String returnMessage = "";
		if (fatherFolder == null) {
			returnMessage = "Wrong path!";
		}
		else if (fatherFolder != null) {
			if (!fatherFolder.getOwnerUsername().equals(username))
				returnMessage = "You have no permission to access this folder!";
			else if (fatherFolder.getOwnerUsername().equals(username)) {
				Folder newFolderOnDatabase = getFolderByPath(folderPath + File.separator + folderName);
				if (newFolderOnDatabase != null)
					returnMessage = "Folder name exists!";
				else {
					File newFolderOnServer = new File(folderPath + File.separator + folderName);
					newFolderOnServer.mkdir();
					newFolderOnDatabase = new Folder(null, fatherFolder.getOwnerUsername(), fatherFolder.getId(), folderName, newFolderOnServer.getPath(), null, 0); 
			        FolderDAOImp.getInstance().Insert(newFolderOnDatabase);
			        returnMessage = "Created sucessfully!";
				}
			}
		}
		
		return returnMessage;
	}
	
	public String changeFolderName(String folderPath, String oldFolderName, String newFolderName, String username) {
		String returnMessage = "";
		Folder oldFolderOnDataBase = getFolderByPath(folderPath + File.separator + oldFolderName);
		if (oldFolderOnDataBase == null) 
			returnMessage = "Folder doesn't exists!";
		else if (oldFolderOnDataBase != null) {
			if (!oldFolderOnDataBase.getOwnerUsername().equals(username))
				returnMessage = "You have no permission to access this folder!";
			else if (oldFolderOnDataBase.getOwnerUsername().equals(username)) {
				if (oldFolderOnDataBase.getName().equals(newFolderName))
					returnMessage = "New name is the same with old one!";
				else {
					File oldFolderOnServer = new File(folderPath + File.separator + oldFolderName);
					File newFolderOnServer = new File(folderPath + File.separator + newFolderName);
					if (newFolderOnServer.exists()) {
						returnMessage = "Folder name exists!";
					}
					else if (oldFolderOnServer.renameTo(newFolderOnServer)) {	
						oldFolderOnDataBase.setName(newFolderName);
						FolderDAOImp.getInstance().Update(oldFolderOnDataBase);	
						FolderDAOImp.getInstance().UpdateAllSubFoldersPathAfterRenameFolder(folderPath, oldFolderName, newFolderName);
						FileDAOImp.getInstance().UpdateAllSubFilesPathAfterRenameFolder(folderPath, oldFolderName, newFolderName);
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
	
	public ArrayList<Folder> searchFolders(String username, String searchContent) {
		ArrayList<Folder> folderArrayList = FolderDAOImp.getInstance().getAll();
		ArrayList<Folder> searchResult = new ArrayList<Folder>();
		for (Folder folder : folderArrayList) {
			if (folder.getOwnerUsername().equals(username) && folder.getName().toLowerCase().contains(searchContent.toLowerCase()))
				searchResult.add(folder);
		}
		return searchResult;
	}
	
	public long folderSize(File directory) {
	    long length = 0;
	    for (File file : directory.listFiles()) {
	        if (file.isFile())
	            length += file.length();
	        else
	            length += folderSize(file);
	    }
	    return length;
	}

	public void updateSizeOfFoldersInPathAfterUpload(String currentFolderPath, long uploadSize) {
		String rootPath = Server.USER_FOLDER_PATH;
		File folderOnserver = new File(currentFolderPath);
		
		while (folderOnserver != null && folderOnserver.getPath().startsWith(rootPath)) {
			Folder folderOnDatabase = getFolderByPath(folderOnserver.getPath());
			if (folderOnDatabase != null) {
				folderOnDatabase.setSize(folderOnDatabase.getSize() + uploadSize);
				FolderDAOImp.getInstance().Update(folderOnDatabase);
			}
			folderOnserver = folderOnserver.getParentFile();
		}
	}
	
	public void updateSizeOfFoldersInPathAfterDelete(String currentFolderPath, long deleteSize) {
		String rootPath = Server.USER_FOLDER_PATH;
		File folderOnserver = new File(currentFolderPath);
		
		while (folderOnserver != null && folderOnserver.getPath().startsWith(rootPath)) {
			Folder folderOnDatabase = getFolderByPath(folderOnserver.getPath());
			if (folderOnDatabase != null) {
				folderOnDatabase.setSize(folderOnDatabase.getSize() - deleteSize);
				FolderDAOImp.getInstance().Update(folderOnDatabase);
			}
			folderOnserver = folderOnserver.getParentFile();
		}
	}
	
}
