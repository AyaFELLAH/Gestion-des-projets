package dataLayer;

import java.sql.ResultSet;

import presentationLayer.models.Notification;
import presentationLayer.models.User;

public interface GestionNotificationInterface {
	public ResultSet getUserNotification(User user);
	public ResultSet getUserNotificationByID(int idNotification);
	public int updateNotificationStatusByID(int idNotification);
	public ResultSet getAllUserNotification(User user);
	public int addNotification(Notification notification);
}
