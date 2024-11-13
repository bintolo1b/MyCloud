package controller.UserHomePageItemController;

import java.io.IOException;
import java.util.ArrayList;

import constant.Server;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.File;
import model.bean.Folder;
import model.bean.MailAttachFile;
import model.bean.User;
import model.bo.FileBO;
import model.bo.FolderBO;
import model.bo.UserBO;
import model.dao.FileDAOImp;
import model.dao.FolderDAOImp;
import model.dao.MailAttachFileDAOImp;


@WebServlet(urlPatterns = {"/userhomepage/main"})
public class UserHomePage_MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//myfun();
		//myfun2();
		//myfunc3();
		//myfunc4();
		//myfunc5();
		
		HttpSession session = req.getSession(false);
		String username = session.getAttribute("username").toString();
		String folderPath = "";
		
		if (req.getParameter("folderPath")!=null)
			folderPath = req.getParameter("folderPath");
		else 
			folderPath = Server.USER_FOLDER_PATH+"\\"+username;
		
		ArrayList<Folder> subFolders = new ArrayList<Folder>();
		ArrayList<File> files = new ArrayList<File>();
		
		Folder currentFolder = FolderBO.getInstance().getFolderByPath(folderPath);
		subFolders = FolderBO.getInstance().getAllSubfolderOfFolder(currentFolder);
		files = FileBO.getInstance().getAllFilesOfFolder(currentFolder);
		User user = UserBO.getInstance().getUser(username);
		
		req.setAttribute("folderPath", folderPath);
		req.setAttribute("subFolders", subFolders);
		req.setAttribute("files", files);
		req.setAttribute("user", user);
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/userHomePageItems/userHomePage_main.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	public void myfun() {
	    ArrayList<Folder> folders = FolderDAOImp.getInstance().getAll();
	    ArrayList<File> files = FileDAOImp.getInstance().getAll();
	    for (File file : files) {
	        String path = file.getPath(); 	    
	        String[] parts = path.split("\\\\");       
	        for (int i = 3; i < parts.length - 1; i++) { 
				for (int j = 0; j < folders.size(); j++) {
					if (folders.get(j).getName().equals(parts[i])) {
						break;
					}
					if (j == folders.size() - 1) {
//						path = path.replace(parts[i], "OOPss");
//						file.setPath(path);	
//						FileDAOImp.getInstance().Update(file);
						System.out.print(path+" ");
						System.out.println(parts[i]);
					}
				}
	        }
	    }
	}
	
	 public void myfun2() {
		 System.out.println("folder");
		    ArrayList<Folder> folders = FolderDAOImp.getInstance().getAll();


		    for (Folder folder : folders) {
		        String path = folder.getPath(); 
		           
		        String[] parts = path.split("\\\\");
		        
		        for (int i = 3; i < parts.length - 1; i++) {
					for (int j = 0; j < folders.size(); j++) {
						if (folders.get(j).getName().equals(parts[i])) {
							break;
						}

						if (j == folders.size() - 1) {
//							path = path.replace(parts[i], "OOPttt");
//							folder.setPath(path);	
//							FolderDAOImp.getInstance().Update(folder);
							System.out.print(path+" ");
							System.out.println(parts[i]);
						}
					}
		        }
		    }
	 }
	 
	 public void myfunc3() {
		 ArrayList<Folder> folders = FolderDAOImp.getInstance().getAll();
		 for(Folder folder : folders) {
			 String path = folder.getPath();
			 java.io.File svfolder = new java.io.File(path);
			 if (svfolder.isDirectory()) {
				long size = FolderBO.getInstance().folderSize(svfolder); 
				if (size != folder.getSize()) {
					System.out.println(folder.getPath() + "not match size");
				}
				folder.setSize(size);
				FolderDAOImp.getInstance().Update(folder);
			 }
		 }
	 }
	 
	 public void myfunc4() {
		 ArrayList<MailAttachFile> mailatts = MailAttachFileDAOImp.getInstance().getAll();
		 for(MailAttachFile mailatt : mailatts) {
             String path = mailatt.getPath();
             java.io.File svfile = new java.io.File(path);
             if (svfile.isFile()) {
                long size = svfile.length();
                mailatt.setSize(size);
                MailAttachFileDAOImp.getInstance().Update(mailatt);
             }
		 }
		 
	 }

	public void myfunc5() {
		java.io.File disk = new java.io.File(Server.DISK_PATH);
		long totalSize = disk.getTotalSpace();
		System.out.println("Total size before cut " + totalSize);
		totalSize = (long)Math.floor(totalSize*1.0/(1024*1024*1024)) * 1024 * 1024 * 1024;
		System.out.println("Total size after cut " + totalSize);
		
		long currentSize = Server.SIZE_FOR_A_USER * UserBO.getInstance().getNumberOfUsers();
		long freeSizeLeft = totalSize - currentSize;
		
		long numberOfUserCanBeCreated = freeSizeLeft / Server.SIZE_FOR_A_USER;
		System.out.println("free size left: "+ freeSizeLeft);
		System.out.println("number of user can be created: "+ numberOfUserCanBeCreated);
	}
	
}
