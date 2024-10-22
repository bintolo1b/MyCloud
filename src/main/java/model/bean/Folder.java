package model.bean;

public class Folder {
	private Integer id;
	private String ownerUsername;
	private Integer parentFolderId;
	private String name;
	private String path;
	
	public Folder(Integer id, String ownerUsername, Integer parentFolderId, String name, String path) {
		super();
		this.id = id;
		this.ownerUsername = ownerUsername;
		this.parentFolderId = parentFolderId;
		this.name = name;
		this.path = path;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOwnerUsername() {
		return ownerUsername;
	}
	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}
	public Integer getParentFolderId() {
		return parentFolderId;
	}
	public void setParentFolderId(Integer parentFolderId) {
		this.parentFolderId = parentFolderId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
