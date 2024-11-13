package model.bo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.mindrot.jbcrypt.BCrypt;

import constant.Server;
import jakarta.servlet.http.Part;
import model.bean.Folder;
import model.bean.User;
import model.dao.MailAttachFileDAOImp;
import model.dao.UserDAOImp;

public class UserBO {
	private static UserBO instance;
	
	private UserBO() {
	}
	
	public static UserBO getInstance() {
		if (instance==null)
			instance = new UserBO();
		return instance;
	}
	
	public boolean doesUserExits(String account) {
		ArrayList<User> userArrayList = UserDAOImp.getInstance().getAll();
		for (User user:userArrayList) {
			if (user.getUsername().equals(account))
				return true;
		}
		return false;
	}
	
	public boolean doesPasswordCorrect(String account, String password) {
		if (!doesUserExits(account))
			return false;
		ArrayList<User> userArrayList = UserDAOImp.getInstance().getAll();
		for (User user:userArrayList) {
			if (user.getUsername().equals(account)) {
				if (BCrypt.checkpw(password, user.getHashedPassword()))
					return true;
				else
					return false;
			}
		}
		return false;
	}
	
	public String CheckLogupNewUser(String username, String password, String verifyPassword, String FullName) {
		String returnMessage = "Logup successfully!";
		if (!checkIfEnoughSpaceToCreateUser()) {
			returnMessage = "Not enough space to create new user!";
		} 
		else if (username.equals("") || password.equals("") || verifyPassword.equals("") || FullName.equals("")) {
			returnMessage = "Lack of information!";
		}
		else if (UserBO.instance.doesUserExits(username)) {
			returnMessage = "Username exists!";
		}
		else if (!UserBO.instance.doesUserExits(username)) {
			if (!password.equals(verifyPassword)) {
				returnMessage = "Verify password is incorrect!";
			}
		}
		return returnMessage;
	}
	
	public boolean checkIfEnoughSpaceToCreateUser() {
		java.io.File disk = new java.io.File(Server.DISK_PATH);
		long totalSize = disk.getTotalSpace();
		totalSize = (long)Math.floor(totalSize*1.0/(1024*1024*1024)) * 1024 * 1024 * 1024;
		
		long currentSize = Server.SIZE_FOR_A_USER * UserBO.getInstance().getNumberOfUsers();
		long freeSizeLeft = totalSize - currentSize;
		
		long numberOfUserCanBeCreated = freeSizeLeft / Server.SIZE_FOR_A_USER;
		if (numberOfUserCanBeCreated > 0)
			return true;
		else
			return false;
	}
	
	public String CheckLogin(String username, String password) {
		String message = "";
		if (UserBO.getInstance().doesUserExits(username)) {
			if (!UserBO.getInstance().doesPasswordCorrect(username, password))
				message = "Password incorrect!";
			else {
				message = "Login successfully!";
		    }
		}
		else {
			message = "Account doesn't exist!";
		}
		return message;
	}
	
	public void addNewUser(String username, String password, String fullName) {
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		User newUser = new User(username, hashedPassword, fullName);
		UserDAOImp.getInstance().Insert(newUser);
	}
	
	public User getUser(String username) {
		ArrayList<User> users = UserDAOImp.getInstance().getAll();
		for (User user:users) {
			if (user.getUsername().equals(username))
				return user;
		}
		return null;
	}
	
	public long getNumberOfUsers() {
		return UserDAOImp.getInstance().getAll().size();
	}
	
	public long getTotalSizeUsedOfAUser(String username) {
		Folder folder = FolderBO.getInstance().getFolderByPath(Server.USER_FOLDER_PATH + "\\" + username);
		long mailAttachFileSize = MailAttachFileDAOImp.getInstance().getMailAttachFileSizeOfUser(username);
		return folder.getSize() + mailAttachFileSize;
	}
	
	public long getFreeSpaceLeftOfUser(String username) {
		return Server.SIZE_FOR_A_USER - getTotalSizeUsedOfAUser(username);
	}
	
	public double getPercetSpaceUsedOfAUser(String username) {
		return getTotalSizeUsedOfAUser(username) * 1.0 / Server.SIZE_FOR_A_USER;
	}
	
	public double roundToTheFirstDecimal(double number) {
		DecimalFormat df = new DecimalFormat("0.0");
		return Double.parseDouble(df.format(number));
	}
	
	public double roundToTheSecondDecimal(double number) {
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.parseDouble(df.format(number));
	}
	
	public boolean checkIfEnoughSpaceToUpload(String username, Collection<Part> parts) {
		long size = 0;
		for (Part part : parts) {
			if (part.getSubmittedFileName() != null)
				size += part.getSize();
		}
		
		if (getFreeSpaceLeftOfUser(username)<size){
			return false;
		}
		return true;
	}

	public String updateFullName(String username, String newFullName) {
		String message = "";
		User user = UserBO.getInstance().getUser(username);
		user.setFullName(newFullName);
		UserDAOImp.getInstance().Update(user);
		message = "Update successfully!";
		return message;
	}

	public String updatePassword(String currentPassword, String newPassword, String verifyNewPassword, String username) {
		String message = "";
		if (!doesPasswordCorrect(username, currentPassword)) {
			message = "Current password is incorrect!";
		} else if (!newPassword.equals(verifyNewPassword)) {
			message = "Verify password is incorrect!";
		} else {
			User user = UserBO.getInstance().getUser(username);
			user.setHashedPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
			UserDAOImp.getInstance().Update(user);
			message = "Update successfully!";
		}
		return message;
	}

	public String updateAvatar(String username, Part part) {
		String message = "";
		if (part.getSubmittedFileName() == null)
			message = "No file choosen!";
		else if (part.getContentType().equals("image/jpeg") || part.getContentType().equals("image/png") || part.getContentType().equals("image/jpg")) {
            File file = new File(Server.USER_AVATAR_PATH + "\\" + username + ".jpg");
			try {
				part.write(file.getAbsolutePath());
				message = "Update successfully!";
			} catch (Exception e) {
				e.printStackTrace();
				message = "Exception occur when writing file!";
			}
        } else {
            message = "File type is not supported!";
        }
		return message;
	}

	public void createDefaultAvatar(String username) {
		File userAvatar = new File(Server.DEFAULT_USER_AVATAR);
		File defaultUserAvatar = new File(Server.USER_AVATAR_PATH + "\\" + username + ".jpg");
		try {
			 InputStream input = new FileInputStream(userAvatar);
			 OutputStream output = new FileOutputStream(defaultUserAvatar);
			 byte[] buffer = new byte[1024];
			 int bytesRead;
			 while ((bytesRead = input.read(buffer)) != -1) {
				 output.write(buffer, 0, bytesRead);
			 }
			 input.close();
			 output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
