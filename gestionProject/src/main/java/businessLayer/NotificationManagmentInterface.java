package businessLayer;

import java.util.List;

import presentationLayer.models.Notification;
import presentationLayer.models.User;

public interface NotificationManagmentInterface {
	public List<Notification> getUserNotification(User user);
	public Notification getUserNotificationByID(int idNotification);
	public int updateNotificationStatusByID(int idNotification);
	public List<Notification> getAllUserNotification(User user);
	public int addNotification(Notification notification);
}
