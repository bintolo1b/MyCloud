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
			if (file.getFolderId()==folder.getId())
				filesOfFolder.add(file);
		}
		return filesOfFolder;
	}
	
	public boolean saveFilesOnServer(String folderPath, Collection<Part> parts) {
		boolean res = true;
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
                    res = false;
                    if (file.exists()) {
                        file.delete(); // Xóa file nếu có lỗi
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
		return res;
	}
	
	public void saveFilesOnDatabase(String folderPath, Collection<Part> parts) {
		Folder folder = FolderBO.getInstance().getFolderByPath(folderPath);
		String ownerUsername = folder.getOwnerUsername();
		int folderId = folder.getId();
		
		for (Part part:parts) {
			if (part.getSubmittedFileName() != null) {
				String fileName = part.getSubmittedFileName();
				String filePath = folderPath + java.io.File.separator + fileName;
				long size = part.getSize();
				LocalDateTime uploadDatetime = LocalDateTime.now();
				
				File newFile = new File(null, ownerUsername, folderId, fileName, filePath, size, uploadDatetime);
				FileDAOImp.getInstance().Insert(newFile);
			}
		}
	}
}
