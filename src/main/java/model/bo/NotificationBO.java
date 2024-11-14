package model.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.bean.Notification;
import model.dao.NotificationDAOImp;

public class NotificationBO {
	private static NotificationBO instance;

	private NotificationBO() {
	}
	
	public static NotificationBO getInstance() {
		if (instance == null)
			instance = new NotificationBO();
		return instance;
	}

	public void insertNotification(String receiverUsername, String content, String sentUsername, String accessLink) {
		Notification notification = new Notification(null, receiverUsername, content, LocalDateTime.now(), "unread", sentUsername, accessLink);
		NotificationDAOImp.getInstance().Insert(notification);
	}

	public ArrayList<Notification> getAllNotification(String username) {
		ArrayList<Notification> allNotifications = NotificationDAOImp.getInstance().getAll();
		ArrayList<Notification> userNotifications = new ArrayList<Notification>();
		for (Notification notification : allNotifications)
			if (notification.getOwnerUsername().equals(username))
				userNotifications.add(notification);
		return userNotifications;
	}
	
	
}
