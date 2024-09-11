package model.bean;

import java.time.LocalDateTime;

public class File {
	private int id;
	private String ownerUsername;
	private int folderId;
	private String name;
	private String path;
	private long size;
	private LocalDateTime uploadDate;
	
	public File(int id, String ownerUsername, int folderId, String name, String path, long size,
			LocalDateTime uploadDate) {
		super();
		this.id = id;
		this.ownerUsername = ownerUsername;
		this.folderId = folderId;
		this.name = name;
		this.path = path;
		this.size = size;
		this.uploadDate = uploadDate;
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

	public int getFolderId() {
		return folderId;
	}

	public void setFolderId(int folderId) {
		this.folderId = folderId;
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

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public LocalDateTime getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	
}
