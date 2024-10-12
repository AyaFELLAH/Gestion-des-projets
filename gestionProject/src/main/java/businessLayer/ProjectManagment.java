package businessLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataLayer.GestionProject;
import dataLayer.GestionProjectInterface;
import presentationLayer.models.Project;

public class ProjectManagment implements ProjectManagmentInterface{
	
	private GestionProjectInterface gestionProject;
	private UserManagmentInterface userManagment;
	
	public ProjectManagment() {
		
		gestionProject = new GestionProject();
		userManagment = new UserManagment();
	}

	@Override
	public int addProject(Project project) {
		
		if(userManagment.existClient(project.getClient())) {
			
			return gestionProject.addProject(project,project.getClient().getCin(),project.getChef().getId());
		} else {
			return gestionProject.addProjectAndClient(project,project.getClient(),project.getChef().getId());
		}
	}

	@Override
	public List<Project> getProjects() {

		List<Project> projects = new ArrayList<>();
		
		ResultSet projectsBD = gestionProject.getProjects();
		try {
			while(projectsBD.next()) {
				
				Project project = new Project(projectsBD.getInt("id"), projectsBD.getString("name"), projectsBD.getString("description"), projectsBD.getInt("nombreJours"), projectsBD.getDate("dateDemarrage"), projectsBD.getDate("dateLivraison"), userManagment.getUserById(projectsBD.getInt("idUser")), userManagment.getClientByCin(projectsBD.getString("cin")));
				project.setDateReunion(projectsBD.getDate("dateReunion"));
				
				projects.add(project);
			}
			
		} catch (SQLException e) {
			
			System.out.println("***** erreur de remplissage des projets dans ggetprojects of pro mang *****");
		}
		return projects;
		
	}

	@Override
	public int numberProjectsByClient(String clientCin) {
		
		int count = 0;
		ResultSet countRS = gestionProject.numberProjectsByClient(clientCin);
		
		try {
			while(countRS.next()) {
				count = countRS.getInt("count");
			}
		} catch (SQLException e) {
			System.out.println("***** probleme lors de calcule de nb de projets par client ds ");
		}
		return count;
		
	}

	@Override
	public int deleteProject(int idProject) {
		
		// je doit determiner le cin de client à partir de idprojet
		String clientCin = userManagment.getClientCinByProject(idProject);
		
		// je doit verifier le nombre de projet avec ce client
		if( numberProjectsByClient(clientCin) == 1 ) {
			if( gestionProject.deleteProject(idProject) == 1 ) {
				return userManagment.deleteClient(clientCin);
			}
		} else {
			return gestionProject.deleteProject(idProject);
		}
		
		return 0;
		
	}

	@Override
	public int updateProject(Project project, String oldClientCin) {
		
		if( userManagment.updateClient(project.getClient(), oldClientCin) == 1 ) {
			return gestionProject.updateProject(project);
		}
		return 0;
		
	}

	@Override
	public List<Project> getProjectsByChef(int idChef) {
		List<Project> projects = new ArrayList<>();
		
		ResultSet projectsBD = gestionProject.getProjectsByChef(idChef);
		try {
			while(projectsBD.next()) {
				
				Project project = new Project(projectsBD.getInt("id"), projectsBD.getString("name"), projectsBD.getString("description"), projectsBD.getInt("nombreJours"), projectsBD.getDate("dateDemarrage"), projectsBD.getDate("dateLivraison"), userManagment.getUserById(projectsBD.getInt("idUser")), userManagment.getClientByCin(projectsBD.getString("cin")));
				project.setDateReunion(projectsBD.getDate("dateReunion"));
				projects.add(project);
			}
			
		} catch (SQLException e) {
			
			System.out.println("***** erreur de remplissage des projets dans getprojectsbychef of pro mang *****");
		}
		return projects;
	}
	
	@Override
	public List<String> getProjectMethodologies() {
		List<String> metho = new ArrayList<>();
		
		ResultSet methoBD = gestionProject.getProjectMethodologies();
		try {
			while(methoBD.next()) {
				
				String m = methoBD.getString("nom");
				metho.add(m);
			}
			
		} catch (SQLException e) {
			
			System.out.println("***** erreur de remplissage des methodologies dans getprojectmethod of pro mang *****");
		}
		return metho;
	}
	
	@Override
	public int addMethAndTech(Project project) {
		if(gestionProject.updateProjectMethodologie(project) == 1)
		{
			gestionProject.addTechnologiesToProject(project);
			return 1;
		}
		return 0;
	}

	@Override
	public boolean updateDateReunion(Project project) {
		if(gestionProject.updateDateReunion(project) == 1)
		return true;
		return false;
	}

	@Override
	public String getProjectName(int idProject) {
		String name= null;
		
		ResultSet nameBD = gestionProject.getProjectName(idProject);
		try {
			while(nameBD.next()) {
				
				name= nameBD.getString("name");
			}
			
		} catch (SQLException e) {
			
			System.out.println("***** erreur de remplissage de name project dans getprojectname of pro mang *****");
		}
		return name;
	}
	
	@Override
	public int verifyProjectNameExistance(String nameProject) {
		int count = 0;
		ResultSet countRS = gestionProject.verifyProjectNameExistance(nameProject);
		
		try {
			while(countRS.next()) {
				count = countRS.getInt("count");
			}
		} catch (SQLException e) {
			System.out.println("***** probleme lors de calcule de nb de projets par nam ds project managment");
		}
		return count;
	}
	
	@Override
	public List<Project> getProjectsByDeveloppeur(int idDeveloppeur) {
		List<Project> projects = new ArrayList<>();
		
		ResultSet projectsBD = gestionProject.getProjectsByDevloppeur(idDeveloppeur);
		try {
			while(projectsBD.next()) {
				
				Project project = new Project(projectsBD.getInt("id"), projectsBD.getString("name"), projectsBD.getString("description"), projectsBD.getInt("nombreJours"), projectsBD.getDate("dateDemarrage"), projectsBD.getDate("dateLivraison"), userManagment.getUserById(projectsBD.getInt("idUser")), userManagment.getClientByCin(projectsBD.getString("cin")));
				projects.add(project);
			}
			
		} catch (SQLException e) {
			
			System.out.println("***** erreur de remplissage des projets dans getprojectsbychef of pro mang *****");
		}
		return projects;
	}

	@Override
	public Project getProjectByid(int id) {
		Project projet = null;
		ResultSet projectBD = gestionProject.getProjectByid(id);
		
		try {
			while(projectBD.next()) {
				System.out.println("getServiceByProject 3fefe");

				projet = new Project(projectBD.getInt("id"), projectBD.getString("name"), projectBD.getString("description"), projectBD.getInt("nombreJours"), projectBD.getDate("dateDemarrage"), projectBD.getDate("dateLivraison"), userManagment.getUserById(projectBD.getInt("idUser")), userManagment.getClientByCin(projectBD.getString("cin")));
			}
		} catch (SQLException e) {
			
			System.out.println("***** erreur lors de chargement de user de getProjectByid dans user man *****");
		}
		
		return projet;
	}
	
}
