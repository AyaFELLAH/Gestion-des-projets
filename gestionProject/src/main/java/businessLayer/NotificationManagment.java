package businessLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataLayer.GestionNotifaction;
import dataLayer.GestionNotificationInterface;
import presentationLayer.models.Notification;
import presentationLayer.models.Project;
import presentationLayer.models.User;

public class NotificationManagment implements NotificationManagmentInterface {

	private GestionNotificationInterface gestionNotification;
	
	
	
	public NotificationManagment() {
		super();
		gestionNotification = new GestionNotifaction();
	}

	@Override
	public List<Notification> getUserNotification(User user) {
		List<Notification> notifications = new ArrayList<>();
		
		ResultSet notificationsBD = gestionNotification.getUserNotification(user);
		try {
			while(notificationsBD.next()) {
				
				Notification notification = new Notification(notificationsBD.getInt("id"), notificationsBD.getInt("idEmetteur"), notificationsBD.getInt("idRecepteur"), notificationsBD.getString("message"), notificationsBD.getDate("dateCreation"));
				notifications.add(notification);
			}
			
		} catch (SQLException e) {
			
			System.out.println("***** erreur de remplissage des notifications dans getnotifaction by user de notif managment *****");
		}
		return notifications;
	}
	
	@Override
	public Notification getUserNotificationByID(int idNotification) {
		
		Notification notification = null;
		
		ResultSet notificationBD = gestionNotification.getUserNotificationByID(idNotification);
		try {
			while(notificationBD.next()) {
				
				notification = new Notification(notificationBD.getInt("id"), notificationBD.getInt("idEmetteur"), notificationBD.getInt("idRecepteur"), notificationBD.getString("message"), notificationBD.getDate("dateCreation"));
				UserManagmentInterface userManagment = new UserManagment();
				User emetteur = userManagment.getUserById(notification.getIdEmetteur());
				
				notification.setEmetteur(emetteur);
			}
			
		} catch (SQLException e) {
			
			System.out.println("***** erreur de remplissage de notification dans getnotifaction by id de notif managment *****");
		}
		return notification;
		
	}
	
	@Override
	public int updateNotificationStatusByID(int idNotification) {
		return gestionNotification.updateNotificationStatusByID(idNotification);
	}
	
	@Override
	public List<Notification> getAllUserNotification(User user) {
		List<Notification> notifications = new ArrayList<>();
		
		ResultSet notificationsBD = gestionNotification.getAllUserNotification(user);
		try {
			while(notificationsBD.next()) {
				
				Notification notification = new Notification(notificationsBD.getInt("id"), notificationsBD.getInt("idEmetteur"), notificationsBD.getInt("idRecepteur"), notificationsBD.getString("message"), notificationsBD.getDate("dateCreation"));
				UserManagmentInterface userManagment = new UserManagment();
				User emetteur = userManagment.getUserById(notification.getIdEmetteur());
				
				notification.setEmetteur(emetteur);
				notifications.add(notification);
			}
			
		} catch (SQLException e) {
			
			System.out.println("***** erreur de remplissage de tous les notifications dans getnotifaction by user de notif managment *****");
		}
		return notifications;
	}
	
	@Override
	public int addNotification(Notification notification) {
		return gestionNotification.addNotification(notification);
	}

}
