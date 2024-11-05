package model.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Folder {
	private Integer id;
	private String ownerUsername;
	private Integer parentFolderId;
	private String name;
	private String path;
	private LocalDateTime uploadDate;
	private long size;
	
	public Folder(Integer id, String ownerUsername, Integer parentFolderId, String name, String path,
			LocalDateTime uploadDate, long size) {
		super();
		this.id = id;
		this.ownerUsername = ownerUsername;
		this.parentFolderId = parentFolderId;
		this.name = name;
		this.path = path;
		this.uploadDate = uploadDate;
		this.size = size;
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
	public LocalDateTime getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getFormattedUploadedDate() {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    return this.uploadDate.format(formatter);
	}
	
	
}
