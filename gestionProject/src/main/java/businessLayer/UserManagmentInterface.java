package businessLayer;

import java.util.List;

import presentationLayer.models.Account;
import presentationLayer.models.Client;
import presentationLayer.models.Project;
import presentationLayer.models.Technology;
import presentationLayer.models.User;

public interface UserManagmentInterface {
	
	public int existeUser(Account account);
	public boolean existClient(Client client);
	public String getUserRole();
	public User getUser();
	public List<User> getChefsProjects();
	public User getUserById(int id);
	public Client getClientByCin(String cin);
	public int deleteClient(String cin);
	public String getClientCinByProject(int idProject);
	public int updateClient(Client client, String oldClientCin) ;
	public List<User> getDeveloppersByTechnologie(Technology technologie);
	public List<User> getDeveloppersByTechnologies(List<Technology> technologies);
	public boolean affectDeveloppersToProject(Project project);
	public List<User> getDeveloppersByProject(String idProject);
	public int updateDevelopper(User developper);
}
