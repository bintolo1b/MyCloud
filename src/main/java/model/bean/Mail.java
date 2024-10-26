package model.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mail {
	private Integer id;
	private String senderUsername;
	private String receiverUsername;
	private String topic;
	private String content;
	private LocalDateTime sentDate;
	private String status;
	
	public Mail(Integer id, String senderUsername, String receiverUsername, String topic, String content,
			LocalDateTime sentDate, String status) {
		super();
		this.id = id;
		this.senderUsername = senderUsername;
		this.receiverUsername = receiverUsername;
		this.topic = topic;
		this.content = content;
		this.sentDate = sentDate;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSenderUsername() {
		return senderUsername;
	}

	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}

	public String getReceiverUsername() {
		return receiverUsername;
	}

	public void setReceiverUsername(String receiverUsername) {
		this.receiverUsername = receiverUsername;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getSentDate() {
		return sentDate;
	}

	public void setSentDate(LocalDateTime sentDate) {
		this.sentDate = sentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getFormattedSentDate() {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    return this.sentDate.format(formatter);
	}
	
}
