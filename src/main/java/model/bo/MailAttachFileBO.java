package model.bo;

import java.util.ArrayList;

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
}
