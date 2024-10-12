package dataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import presentationLayer.models.Client;
import presentationLayer.models.Project;
import presentationLayer.models.Technology;

public class GestionProject implements GestionProjectInterface{
	private Connexion connexion = null;
	private Statement statement;
	
	public GestionProject() {
		connexion = new Connexion();
	}
	
	@Override
	public int addProject(Project projet, String cin, int idChefP) {
		
		int rowAffected = 0;
		connexion.connect();
		System.out.println(cin+" "+idChefP);
		try {
			String requete="insert into projet(name,description,nombreJours,dateDemarrage,dateLivraison, cin, idUser,dateReunion) values('"+projet.getName()+"','"+projet.getDescription()+"','"+projet.getNombreJours()+"','"+projet.getDateDemarrage()+"','"+projet.getDatelivraison()+"','"+cin+"','"+idChefP+"',null)";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			rowAffected = statement.executeUpdate();
			System.out.println("***** statement d'insertion de projet avec succes *****");
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de creation de statement d'insertion de projet *****");
		}
		
		connexion.disconnect();
		return rowAffected;
	}
	
	@Override
	public int addProjectAndClient(Project projet, Client client,int idChefP) {
		
		int rowAffected = 0;
		
		
		connexion.connect();
		
		try {			
			String requete="insert into client(cin,nom,prenom,telephone) values('"+client.getCin()+"','"+client.getNom()+"','"+client.getPrenom()+"','"+client.getTelephone()+"')";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			rowAffected = statement.executeUpdate();
			System.out.println("***** statement d'insertion de client avec succes *****");
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de creation de statement d'insertion de client *****");
		}
		
		connexion.disconnect();
		
		if( rowAffected == 1) {
			return this.addProject(projet, client.getCin(),idChefP);
		}
		
		return rowAffected;
	}
	
	@Override
	public ResultSet getProjects() {
		ResultSet projects = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select * from projet order By id desc";
			projects = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de creation de statement dans getprojects de Gest project *****");
		}
		return projects;
	}
	
	@Override
	public int deleteProject(int id) {
		int status = 0;		
		connexion.connect();
		
		try {			
			String requete="delete from projet where id='"+id+"'";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			status = statement.executeUpdate();
			System.out.println("***** statement suppresion de projet avec succes *****");
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de suppresion de projet en gest user *****");
		}
		
		connexion.disconnect();
		
		return status;
	}
	
	@Override
	public ResultSet numberProjectsByClient(String clientCin) {
		ResultSet count = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select count(*) as count from projet where cin='"+clientCin+"'";
			count = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de calcule le nb de projets par client de Gest project *****");
		}
		return count;
	}
	
	@Override
	public int updateProject(Project project) {
		int status = 0;
		connexion.connect();
		
		System.out.println(project.getId()+"\t"+project.getDescription());
		
		try {	
			int id = project.getId();
			
			String requete="update projet set name='"+project.getName()+"', description='"+ project.getDescription()+"', dateDemarrage='"+project.getDateDemarrage()+"', nombreJours='"+project.getNombreJours()+"', dateLivraison='"+project.getDatelivraison()+"', idUser='"+project.getChef().getId()+"', cin='"+project.getClient().getCin()+"' where projet.id='"+id+"'";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			status = statement.executeUpdate();
			System.out.println("$$$$$ statement modification de projet avec succes $$$$$ "+status);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de modification de projet en gest projet *****");
		}
		
		connexion.disconnect();
		return status;
	}

	@Override
	public ResultSet getProjectsByChef(int idChef) {
		ResultSet projects = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select * from projet where idUser='"+idChef+"' order By id desc";
			projects = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de creation de statement dans getprojectsbychef de Gest project *****");
		}
		return projects;
	}
	
	@Override
	public ResultSet getProjectMethodologies(){
		ResultSet methodologies = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select * from methodologie";
			methodologies = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de creation de statement dans getprojectMethodologies de Gest project *****");
		}
		return methodologies;
	}
	
	@Override
	public int updateProjectMethodologie(Project project) {
		int status = 0;
		connexion.connect();
		
		try {	
			int id = project.getId();
			
			String requete="update projet set methodologie='"+project.getMethodologie()+"' where projet.id='"+id+"'";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			status = statement.executeUpdate();
			System.out.println("$$$$$ statement modification de projet avec succes $$$$$ "+status);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de modification de mth de projet en gest projet *****");
		}
		
		connexion.disconnect();
		return status;
	}
	
	@Override
	public int addTechnologiesToProject(Project project) {
		connexion.connect();
		
		try {			
			String requete="delete from projet_developpeur where idProjet='"+project.getId()+"'";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			statement.executeUpdate();
			System.out.println("***** statement supresion projet devloppeur de gest user avec succes *****");
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de suppresion de projet devloppeur en gest project *****");
			return 0;
		}
		
		try {			
			String requete="delete from projet_technologie where idProjet='"+project.getId()+"'";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			statement.executeUpdate();
			System.out.println("***** statement suppresion de projet et techs de projet_tech avec succes *****");
			
			for(Technology technologie: project.getTechnologies()) {
				
				try {			
					requete="insert into projet_technologie(idProjet,idTechnologie) values('"+project.getId()+"','"+technologie.getId()+"')";
					statement = connexion.getConnection().prepareStatement(requete);
					
					statement.executeUpdate();
					System.out.println("***** statement d'insertion de projet_technologie avec succes *****");
					
				} catch (SQLException e) {
					System.out.println("***** Erreur lors de creation de statement d'insertion de projet_technologie *****");
				}
				
			}
			
			return 1;
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de suppresion projet et techs de projet_tech en gest project *****");
		}
		
		connexion.disconnect();
		
		return 0;
	}

	@Override
	public int updateDateReunion(Project project) {
		int status = 0;
		connexion.connect();
		
		try {	
			int id = project.getId();
			
			String requete="update projet set dateReunion='"+project.getDateReunion()+"' where projet.id='"+id+"'";
			PreparedStatement statement = connexion.getConnection().prepareStatement(requete);
			
			status = statement.executeUpdate();
			System.out.println("$$$$$ statement modification de date reunion projet avec succes $$$$$ "+status);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de modification de date reunion  de projet en gest projet *****");
		}
		
		connexion.disconnect();
		return status;
	}

	@Override
	public ResultSet getProjectName(int idProject) {
		ResultSet projectName = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select name from projet where id='"+idProject+"'";
			projectName = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de recup de name prject de statement de Gest project *****");
		}
		return projectName;
	}
	
	@Override
	public ResultSet verifyProjectNameExistance(String namePrpject) {
		ResultSet count = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select count(*) as count from projet where name='"+namePrpject+"'";
			count = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de calcule le nb de projets par name de Gest project *****");
		}
		return count;
	}
	
	@Override
	public ResultSet getProjectsByDevloppeur(int idDevloppeur) {
		ResultSet projects = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly *****");
			
			String requete="Select distinct p.* from projet p, projet_developpeur pd where p.id = pd.idProjet and pd.idDeveloppeur='"+idDevloppeur+"' order By id desc";
			projects = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de creation de statement dans getprojectsbychef de Gest project *****");
		}
		return projects;
	
	}
	
	@Override
	public ResultSet getProjectByid(int id) {
		ResultSet project = null;
		connexion.connect();
		
		try {
			statement = connexion.getConnection().createStatement();
			System.out.println("***** Statement created successfly getProjectByid*****");
			
			String requete="Select * from projet where id="+id+"";
			project = statement.executeQuery(requete);
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de creation de statement dans getProjectByid de Gest projet *****");
		}
		return project;
	}
}
