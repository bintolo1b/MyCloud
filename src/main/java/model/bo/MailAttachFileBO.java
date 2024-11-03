package model.bo;

import java.util.ArrayList;

import model.bean.Mail;
import model.bean.MailAttachFile;
import model.dao.MailAttachFileDAOImp;

public class MailAttachFileBO {
	private static MailAttachFileBO instance;
	private MailAttachFileBO() {
	}
	
	public static MailAttachFileBO getInstance() {
		if (instance == null)
			instance = new MailAttachFileBO();
		return instance;
	}
	
	public ArrayList<MailAttachFile> getAllMailAttachFile(){
		return MailAttachFileDAOImp.getInstance().getAll();
	}
	
	public ArrayList<MailAttachFile> getAllMailAttachFileOfMail(int mailId){
		ArrayList<MailAttachFile> mailAttachFiles = getAllMailAttachFile();
		ArrayList<MailAttachFile> returnArrList = new ArrayList<MailAttachFile>();
		for (MailAttachFile mailAttachFile:mailAttachFiles)
			if ((int)mailAttachFile.getMailId() == mailId)
				returnArrList.add(mailAttachFile);
		return returnArrList;
	}
	
	public MailAttachFile getMailAttachFileByPath(String mailAttachFilePath) {
		ArrayList<MailAttachFile> mailAttachFiles = getAllMailAttachFile();
		for (MailAttachFile mailAttachFile : mailAttachFiles)
			if (mailAttachFile.getPath().equals(mailAttachFilePath))
				return mailAttachFile;
		return null;
	}

	public boolean checkIfUserHavePermissionToDownloadMailAttachFile(String username, MailAttachFile mailAttachFile) {
		Mail mail = MailBO.getInstance().getMailById(mailAttachFile.getMailId());
		if (mail.getSenderUsername().equals(username) || mail.getReceiverUsername().equals(username))
			return true;
		return false;
	}
}
