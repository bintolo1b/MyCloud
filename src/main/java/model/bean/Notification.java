package model.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notification {
	private Integer id;
	private String ownerUsername;
	private String content;
	private LocalDateTime time;
	private String status;
	private String sentUsername;
	private String accessLink;
		
	
	public Notification(Integer id, String ownerUsername, String content, LocalDateTime time, String status,
			String sentUsername, String accessLink) {
		super();
		this.id = id;
		this.ownerUsername = ownerUsername;
		this.content = content;
		this.time = time;
		this.status = status;
		this.sentUsername = sentUsername;
		this.accessLink = accessLink;
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



	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSentUsername() {
		return sentUsername;
	}

	public void setSentUsername(String sentUsername) {
		this.sentUsername = sentUsername;
	}

	public String getAccessLink() {
		return accessLink;
	}

	public void setAccessLink(String accessLink) {
		this.accessLink = accessLink;
	}

	public String getFormattedTime() {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    return this.time.format(formatter);
	}
	
}	
