package dataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import presentationLayer.models.Technology;

public class GestionTechnologie implements GestionTechnologieInterface{

	private Connexion connexion = null;
	private Statement statement;
	
	public GestionTechnologie() {
		super();
		connexion = new Connexion();
	}

	@Override
	public ResultSet getTechnologies() {
		ResultSet technologies = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select * from technologie";
			technologies = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de creation de statement dans gettech de Gest tech *****");
		}
		return technologies;
	}
	
	@Override
	public ResultSet getTechnologyById(int idTechnology) {
		
		ResultSet technologie = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select * from technologie where id='"+idTechnology+"'";
			technologie = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de creation de statement dans gettech de Gest tech *****");
		}
		return technologie;
		
	}
	
	@Override
	public ResultSet getTechnologiesByDevelopper(int idDevelopper) {
		ResultSet technologies = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select t.* from technologie t, developpeur_technologie dt where dt.idTechnologie = t.id and dt.idDeveloppeur ='"+idDevelopper+"'";
			technologies = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de creation de statement dans gettech de Gest tech *****");
		}
		return technologies;
	}
	
	@Override
	public int addTechnology(Technology technology) {
		int status = 0;
		connexion.connect();
		
		try {	
			
			String requete="insert into technologie (nom,description) values('"+technology.getNom()+"','"+technology.getDescription()+"')";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			status = statement.executeUpdate();
			System.out.println("***** statement d'insertion  de technologie avec succes *****"+status);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors d'insertion de technologie en gest technologie *****");
		}
		
		connexion.disconnect();
		return status;
	}
	
	@Override
	public ResultSet getTechnologyIdByFields(Technology technology) {
		ResultSet technologie = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select id from technologie where nom='"+technology.getNom()+"' and description='"+technology.getDescription()+"'";
			technologie = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de creation de statement dans gettech de Gest tech *****");
		}
		return technologie;
	}
	
	@Override
	public int addTechnologyToDevelopper(Technology technology) {
		int status = 0;
		connexion.connect();
		
		try {	
			
			String requete="insert into developpeur_technologie (idDeveloppeur,idTechnologie) values('"+technology.getIdDevelopper()+"','"+technology.getId()+"')";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			status = statement.executeUpdate();
			System.out.println("***** statement d'insertion d'jout technologie  au developpeur avec succes *****"+status);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors d'jout technologie  au developpeur  en gest technologie *****");
		}
		
		connexion.disconnect();
		return status;
	}
}
