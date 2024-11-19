package model.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.bean.Notification;
import model.bean.User;
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

	public int insertNotification(String receiverUsername, String content, String sentUsername, String accessLink) {
		Notification notification = new Notification(null, receiverUsername, content, LocalDateTime.now(), "unread", sentUsername, accessLink);
		return NotificationDAOImp.getInstance().InsertAndReturnNewId(notification);
	}

	public ArrayList<Notification> getAllNotification(String username) {
		ArrayList<Notification> allNotifications = NotificationDAOImp.getInstance().getAll();
		ArrayList<Notification> userNotifications = new ArrayList<Notification>();
		for (Notification notification : allNotifications)
			if (notification.getOwnerUsername().equals(username))
				userNotifications.add(notification);
		return userNotifications;
	}
	
	public Notification getNotificationById(int notificationId) {
		ArrayList<Notification> allNotifications = NotificationDAOImp.getInstance().getAll();
		for (Notification notification : allNotifications)
			if ((int)notification.getId() == notificationId)
				return notification;
		return null;
	}
	
	public String checkReadNotification(int notificationId, String username) {
		String message = "";
        Notification notification = getNotificationById(notificationId);
        if (notification == null)
        	message = "Notification not found!";
        else {
        	User user = UserBO.getInstance().getUser(username);
			if (notification.getOwnerUsername().equals(user.getUsername())) {
				notification.setStatus("read");
				NotificationDAOImp.getInstance().Update(notification);
				message = "Notification read!";
			} else
				message = "You do not have permission to access this notification.";
        }
        return message;
    }
}
