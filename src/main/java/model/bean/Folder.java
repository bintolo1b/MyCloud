package model.bean;

public class Folder {
	private int id;
	private String ownerUsername;
	private int parentFolderId;
	private String name;
	private String path;
	
	public Folder(int id, String ownerUsername, int parentFolderId, String name, String path) {
		super();
		this.id = id;
		this.ownerUsername = ownerUsername;
		this.parentFolderId = parentFolderId;
		this.name = name;
		this.path = path;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwnerUsername() {
		return ownerUsername;
	}

	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}

	public int getParentFolderId() {
		return parentFolderId;
	}

	public void setParentFolderId(int parentFolderId) {
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
