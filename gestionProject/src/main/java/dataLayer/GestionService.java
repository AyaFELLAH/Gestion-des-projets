package dataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import presentationLayer.models.Service;

public class GestionService implements GestionServiceInterface {
	private Connexion connexion = null;
	private Statement statement;
	
	public GestionService() {
		super();
		connexion = new Connexion();
	}

	@Override
	public int addService(Service service) {
		int status = 0;
		connexion.connect();
		
		try {	
			
			String requete="insert into service (nom,description,duree,idProjet) values('"+service.getNom()+"','"+service.getDescription()+"','"+service.getDuree()+"','"+service.getIdProject()+"')";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			status = statement.executeUpdate();
			System.out.println("***** statement d'insertion  de service avec succes *****"+status);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors d'insertion de service en gest service *****");
		}
		
		connexion.disconnect();
		return status;
	}

	@Override
	public ResultSet getServicesByProject(int idProject) {
		ResultSet services = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select * from service where idProjet='"+idProject+"'";
			services = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de recuperation de statement dans getservice by project de Gest user *****");
		}
		return services;
	}
	
	@Override
	public ResultSet getIdService(Service service) {
		ResultSet idService = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select id from service where idProjet='"+service.getIdProject()+"' and nom='"+service.getNom()+"' and description='"+service.getDescription()+"' and duree='"+service.getDuree()+"'";
			idService = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de recuperation de id service  getservice de gest service *****");
		}
		return idService;
	}

}
