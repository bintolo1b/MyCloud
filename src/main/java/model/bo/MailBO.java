package model.bo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import constant.Server;
import jakarta.servlet.http.Part;
import model.bean.Mail;
import model.bean.MailAttachFile;
import model.dao.MailAttachFileDAOImp;
import model.dao.MailDAOImp;

public class MailBO {
	private static MailBO instance;
	private MailBO() {
		
	}
	public static MailBO getInstance() {
		if (instance == null)
			instance = new MailBO();
		return instance;
	}
	
	public ArrayList<Mail> getAllMail(){
		return MailDAOImp.getInstance().getAll();
	}
	
	
	public int generateNewMailId() {
		return MailDAOImp.getInstance().getLatestId()+1;
	}
	
	public ArrayList<Mail> getAllReceivedMail(String username){
		ArrayList<Mail> allMails = MailDAOImp.getInstance().getAll();
		ArrayList<Mail> receivedMails = new ArrayList<Mail>();
		for (Mail mail:allMails)
			if (mail.getReceiverUsername().equals(username))
				receivedMails.add(mail);
		return receivedMails;
				
	}
	
	public Mail getMailById(int id) {
		ArrayList<Mail> mails = getAllMail();
		for (Mail mail:mails)
			if (mail.getId() == id)
				return mail;
		return null;
	}
	
	public void saveMailAttachFilesOnServer(int mailId, String receiverUsername, Collection<Part> parts) {
		File newMailFolder = new File(Server.USER_MAIL_PATH + File.separator + mailId);
		if (!newMailFolder.exists()) 
			newMailFolder.mkdir();
		
		
		for (Part part:parts)
			if (part.getSubmittedFileName() != null) {
				InputStream in;
				OutputStream out;
				try {
					String attachFileName = part.getSubmittedFileName();
					File attachFile = new File(newMailFolder.getPath() + File.separator + attachFileName);
					
					in = part.getInputStream();
					out = new FileOutputStream(attachFile);
					
					byte[] buffer = new byte[1024];
					int byteReads;
					while ((byteReads = in.read(buffer)) != -1) {
						out.write(buffer, 0, byteReads);
					}
					out.flush();
					
					MailAttachFile newMailAttachFile = new MailAttachFile(null, mailId, attachFile.getPath(), attachFileName);
					MailAttachFileDAOImp.getInstance().Insert(newMailAttachFile);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
	
	public String sendMail(String senderUsername, String receiverUsername, String topic, String content, Collection<Part> parts) {
		if (senderUsername.equals("") || receiverUsername.equals("") || topic.equals("") || content.equals("")) {
			return "Lack of information!";
		}
		
		if (UserBO.getInstance().getUser(receiverUsername) == null)
			return "Username doesn't exist!";
		
		if (senderUsername.equals(receiverUsername))
			return "Sender and receiver is the same!";
		
		int newMailId = generateNewMailId();
		Mail newMail = new Mail(newMailId, senderUsername, receiverUsername, topic, content, LocalDateTime.now() ,"Pending");
		MailDAOImp.getInstance().Insert(newMail);
		
		saveMailAttachFilesOnServer(newMailId, receiverUsername ,parts);
		
		return "Sent Successfully!";
	}
}
