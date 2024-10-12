package dataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import presentationLayer.models.Notification;
import presentationLayer.models.User;

public class GestionNotifaction implements GestionNotificationInterface {

	private Connexion connexion = null;
	private Statement statement;
	
	
	public GestionNotifaction() {
		super();
		connexion = new Connexion();
	}

	@Override
	public ResultSet getUserNotification(User user) {
		ResultSet notifications = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select * from notification where status= 0 and idRecepteur='"+user.getId()+"' order By id desc limit 5";
			notifications = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de recuperation de notifications gest notifi *****");
		}
		
		return notifications;
	}
	
	@Override
	public ResultSet getUserNotificationByID(int idNotification) {
		ResultSet notification = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select * from notification where id='"+idNotification+"'";
			notification = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de recuperation de notification gest notifi by id *****");
		}
		
		return notification;
	}
	
	@Override
	public int updateNotificationStatusByID(int idNotification) {
		int status = 0;
		connexion.connect();
		
		try {	
			
			String requete="update notification set status=1 where id='"+idNotification+"'";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			status = statement.executeUpdate();
			System.out.println("***** statement modification de notification avec succes *****"+status);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de modification de status de notification en gest notification *****");
		}
		
		connexion.disconnect();
		return status;
	}

	@Override
	public ResultSet getAllUserNotification(User user) {
		ResultSet notifications = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select * from notification where idRecepteur='"+user.getId()+"' order By id desc";
			notifications = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de recuperation de tous les notifications gest notifi *****");
		}
		
		return notifications;
	}
	
	@Override
	public int addNotification(Notification notification) {
		int status = 0;
		connexion.connect();
		
		try {	
			
			String requete="insert into notification (message,idEmetteur,idRecepteur) values('"+notification.getMessage()+"','"+notification.getIdEmetteur()+"','"+notification.getIdRecepteur()+"')";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			status = statement.executeUpdate();
			System.out.println("***** statement d'insertion  de notification avec succes *****"+status);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors d'insertion de notification en gest notification *****");
		}
		
		connexion.disconnect();
		return status;
	}
	
}
