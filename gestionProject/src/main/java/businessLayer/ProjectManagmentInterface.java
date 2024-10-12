package businessLayer;

import java.util.List;

import presentationLayer.models.Project;

public interface ProjectManagmentInterface {
	
	public int addProject(Project project);
	public List<Project> getProjects();
	public int numberProjectsByClient(String clientCin);
	public int deleteProject(int idProject);
	public int updateProject(Project project, String oldClientCin);
	public List<Project> getProjectsByChef(int idChef);
	public List<String> getProjectMethodologies();
	public int addMethAndTech(Project project);
	public boolean updateDateReunion(Project project);
	public String getProjectName(int idProject);
	public int verifyProjectNameExistance(String namePrpject);

	public List<Project> getProjectsByDeveloppeur(int idDeveloppeur);
	public Project getProjectByid(int id);
}
