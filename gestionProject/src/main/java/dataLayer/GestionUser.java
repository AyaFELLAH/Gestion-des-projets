package dataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import businessLayer.NotificationManagment;
import businessLayer.NotificationManagmentInterface;
import presentationLayer.models.*;

public class GestionUser implements GestionUserInterface{
	private Connexion connexion = null;
	private Statement statement;
	
	public GestionUser() {
		connexion = new Connexion();
	}
	
	@Override
	public ResultSet getUsers() {
		ResultSet resultSet = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select * from user";
			resultSet = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.err.println("***** Erreur lors de creation de statement dans getUser de Gest Connex *****");
		}
		
		return resultSet;
	}

	@Override
	public Connexion getConnexion() {
		return connexion;
	}
	
	@Override
	public ResultSet getClients() {
		
		ResultSet resultSet = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select * from client";
			resultSet = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.err.println("***** Erreur lors de creation de statement dans getclients de Gest user *****");
		}
		return resultSet;
	}
	
	@Override
	public ResultSet getUserById(int id) {
		ResultSet user = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select * from user where id='"+id+"'";
			user = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.err.println("***** Erreur lors de creation de statement dans getuserbyid de Gest user *****");
		}
		return user;
	}
	
	@Override
	public ResultSet getClientByCin(String cin) {
		
		ResultSet client = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select * from client where cin='"+cin+"'";
			client = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.err.println("***** Erreur lors de creation de statement dans getclientbycin de Gest user *****");
		}
		return client;
	}
	
	@Override
	public ResultSet getClientCinByProject(int idProject) {
		
		ResultSet clientCin = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select cin from projet where id='"+idProject+"'";
			clientCin = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.err.println("***** Erreur lors de recuperation de client cin de Gest user *****");
		}
		return clientCin;
	}
	
	@Override
	public int deleteClient(String cin) {
		
		int status = 0;		
		connexion.connect();
		
		try {			
			String requete="delete from client where cin='"+cin+"'";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			status = statement.executeUpdate();
			System.out.println("***** statement suppresion de client avec succes *****");
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de suppresion de client en gest user *****");
		}
		
		connexion.disconnect();
		
		return status;
	}
	
	@Override
	public int updateClient(Client client, String oldClientCin) {
		
		int status = 0;
		connexion.connect();
		
		try {			
			String requete="update client set cin='"+client.getCin()+"', nom='"+client.getNom()+"', prenom='"+client.getPrenom()+"', telephone='"+client.getTelephone()+"' where cin='"+oldClientCin+"'";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			status = statement.executeUpdate();
			System.out.println("***** statement modification de client avec succes *****");
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de modification de client en gest user *****");
		}
		
		connexion.disconnect();
		return status;
	}
	
	@Override
	public ResultSet getDeveloppersByTechnologie(Technology technologie) {
		
		ResultSet developpers = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select d.* from user d, developpeur_technologie dt where d.id = dt.idDeveloppeur and dt.idTechnologie='"+technologie.getId()+"'";
			developpers = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de recuperation de developpers by tech de Gest user *****");
		}
		return developpers;
		
	}

	@Override
	public boolean affectDeveloppersToProject(Project project) {
		
		connexion.connect();
		
		try {			
			String requete="delete from projet_developpeur where idProjet='"+project.getId()+"'";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			statement.executeUpdate();
			System.out.println("***** statement supresion projet devloppeur de gest user avec succes *****");
			
		} catch (SQLException e) {
			System.err.println("***** Erreur lors de suppresion de projet devloppeur en gest user *****");
			return false;
		}
		
		for(User developpeur: project.getDeveloppeurs()) {
		
			try {
								
				String requete="insert into projet_developpeur( idProjet, idDeveloppeur) values('"+project.getId()+"','"+developpeur.getId()+"')";
				PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
				
				statement.executeUpdate();
				System.out.println("***** statement d'insertion de projet et developpeur avec succes *****");
				
				// now we affected the developper to the project
				Notification notification = new Notification();
				notification.setIdEmetteur(project.getChef().getId());
				notification.setIdRecepteur(developpeur.getId());
				String message = "Vous avez affecté au projet ~ "+project.getName()+" ~, et vous avez une réunion le "+project.getDateReunion();
				System.out.println(message);
				notification.setMessage(message);
				
				GestionNotifaction gestionNotifaction = new GestionNotifaction();
				gestionNotifaction.addNotification(notification);
				
			} catch (SQLException e) {
				System.err.println("***** Erreur lors de creation de statement d'insertion de projet et developpeur en gest user *****");
				return false;
			}
			
		}
		
		connexion.disconnect();
		return true;
	}

	@Override
	public ResultSet getDeveloppersByProject(String idProject) {
		ResultSet developpers = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select d.* from user d, projet_developpeur pd where d.id = pd.idDeveloppeur and pd.idProjet='"+idProject+"'";
			developpers = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.err.println("***** Erreur lors de recuperation de developpers by project de Gest user *****");
		}
		return developpers;
	}
	
	@Override
	public int updateDevelopper(User developper) {
		int status = 0;
		connexion.connect();
		
		try {			
			String requete="update user set nom='"+developper.getNom()+"', prenom='"+developper.getPrenom()+"', login='"+developper.getAccount().getLogin()+"' where id='"+developper.getId()+"'";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			status = statement.executeUpdate();
			System.out.println("***** statement modification de developpeur avec succes *****");
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de modification de developpeur en gest user *****");
		}
		
		connexion.disconnect();
		return status;
	}
}
