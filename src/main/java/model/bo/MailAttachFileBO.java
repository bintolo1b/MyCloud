package model.bo;

public class MailAttachFileBO {
	private static MailAttachFileBO instance;
	private MailAttachFileBO() {
	}
	
	public static MailAttachFileBO getInstance() {
		if (instance == null)
			instance = new MailAttachFileBO();
		return instance;
	}
	
	
}
