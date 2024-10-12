package dataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import presentationLayer.models.Tache;
import presentationLayer.models.Technology;

public class GestionTache implements GestionTacheInterface{
	private Connexion connexion = null;
	private Statement statement;
	
	
	
	public GestionTache() {
		super();
		connexion = new Connexion();
	}



	@Override
	public ResultSet getTachesByService(int idService) {
		ResultSet taches = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select * from tache where idService='"+idService+"'";
			taches = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.err.println("***** Erreur lors de recuperation des taches dans get taches by service de Gest tache *****");
		}
		return taches;
	}
	
	@Override
	public int addTache(Tache tache) {
		int status = 0;
		connexion.connect();
		
		try {	
			
			String requete="insert into tache (nom,description,idService,idDeveloppeur) values('"+tache.getNom()+"','"+tache.getDescription()+"','"+tache.getIdService()+"','"+tache.getIdDeveloppeur()+"')";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			status = statement.executeUpdate();
			System.out.println("***** statement d'insertion  de tache avec succes *****"+status);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors d'insertion de tache en gest tache *****");
		}
		
		connexion.disconnect();
		return status;
	}
	
	@Override
	public int changeTacheProgress(int idTache, int progress) {
		int status = 0;
		connexion.connect();
		
		try {
			
			String requete="update tache set pourcentage='"+progress+"' where id='"+idTache+"'";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			status = statement.executeUpdate();
			System.out.println("$$$$$ statement modification de pourcentage reunion du tache avec succes $$$$$ "+status);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de modification de pourcentage en gestion tache *****");
		}
		
		connexion.disconnect();
		return status;
	}

}
