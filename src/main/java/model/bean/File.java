package model.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class File {
	private Integer id;
	private String ownerUsername;
	private int folderId;
	private String name;
	private String path;
	private long size;
	private LocalDateTime uploadDate;
	
	public File(Integer id, String ownerUsername, int folderId, String name, String path, long size,
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
	public String getFormattedUploadedDate() {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    return this.uploadDate.format(formatter);
	}
	
}
