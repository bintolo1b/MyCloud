package model.bean;

public class MailAttachFile {
	private Integer id;
	private Integer mailId;
	private String path;
	private String name;
	private long size;
	
	public MailAttachFile(Integer id, Integer mailId, String path, String name, long size) {
		super();
		this.id = id;
		this.mailId = mailId;
		this.path = path;
		this.name = name;
		this.size = size;
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

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
	
	
}
