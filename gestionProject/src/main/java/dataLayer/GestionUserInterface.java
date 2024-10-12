package dataLayer;

import java.sql.ResultSet;

import presentationLayer.models.Client;
import presentationLayer.models.Project;
import presentationLayer.models.Technology;
import presentationLayer.models.User;

public interface GestionUserInterface {

	public ResultSet getUsers();
	public Connexion getConnexion();
	public ResultSet getClients();
	public ResultSet getUserById(int id);
	public ResultSet getClientByCin(String cin);
	public ResultSet getClientCinByProject(int idProject);
	public int deleteClient(String cin);
	public int updateClient(Client client, String oldClientCin);
	public ResultSet getDeveloppersByTechnologie(Technology technologie);
	public boolean affectDeveloppersToProject(Project project);
	public ResultSet getDeveloppersByProject(String idProject);
	public int updateDevelopper(User developper);
}
