package model.bean;

public class MailAttachFile {
	private Integer id;
	private Integer mailId;
	private String path;
	private String name;
	
	public MailAttachFile(Integer id, Integer mailId, String path, String name) {
		super();
		this.id = id;
		this.mailId = mailId;
		this.path = path;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMailId() {
		return mailId;
	}

	public void setMailId(Integer mailId) {
		this.mailId = mailId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
