package dataLayer;

import java.sql.ResultSet;

import presentationLayer.models.Client;
import presentationLayer.models.Project;

public interface GestionProjectInterface {

	public int addProject(Project projet, String cin, int idChefP);
	public int addProjectAndClient(Project projet, Client client,int idChefP);
	public ResultSet getProjects();
	public int deleteProject(int id);
	public ResultSet numberProjectsByClient(String clientCin);
	public int updateProject(Project project);
	public ResultSet getProjectsByChef(int idChef);
	public ResultSet getProjectMethodologies();
	public int updateProjectMethodologie(Project project);
	public int addTechnologiesToProject(Project project);
	public int updateDateReunion(Project project);
	public ResultSet getProjectName(int idProject);
	public ResultSet verifyProjectNameExistance(String namePrpject);
	public ResultSet getProjectsByDevloppeur(int idDevloppeur);
	public ResultSet getProjectByid(int id);
}
