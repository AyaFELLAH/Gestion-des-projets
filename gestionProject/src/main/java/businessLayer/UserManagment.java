package businessLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataLayer.GestionUser;
import dataLayer.GestionUserInterface;
import presentationLayer.models.*;

public class UserManagment implements UserManagmentInterface{
	private GestionUserInterface gestionUser;
	private User user;
	
	public UserManagment() {
		gestionUser = new GestionUser();
	}
	
	@Override
	public int existeUser(Account account) {
		
		int test = 0;
		ResultSet usersRS = gestionUser.getUsers();
		try {
			while(usersRS.next()) {
				if(usersRS.getString("login").equals(account.getLogin())) {
					test = 1;
					if(usersRS.getString("password").equals(account.getPassword())) {
						user = new User( usersRS.getString("nom"), usersRS.getString("prenom"), Integer.parseInt(usersRS.getString("id")), usersRS.getString("role"), account);
						return 1;
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("**** Erreur lors de traitement de connexion de user *****");
		}
		gestionUser.getConnexion().disconnect();
		if(test == 1)
			return 2;
		return test;
		
	}
	
	@Override
	public boolean existClient(Client client) {
		boolean existC = false;
		
		ResultSet clients = gestionUser.getClients();
		
		try {
			while(clients.next()) {
				if(clients.getString("cin").equals(client.getCin())) {
					existC = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("*****  erreur lors de test d'existance de client *****");
		}
		
		return existC;
	}
	
	@Override
	public String getUserRole() {
		return user.getRole();
	}

	@Override
	public User getUser() {
		return user;
	}
	
	@Override
	public List<User> getChefsProjects(){
		List<User> users = new ArrayList<>();
		
		ResultSet resUsers = gestionUser.getUsers();
		try {
			while(resUsers.next()) {
				if(resUsers.getString("role").equals("chefProject")) {
					User user = new User(resUsers.getString("nom"),resUsers.getString("prenom"),Integer.parseInt(resUsers.getString("id")),resUsers.getString("role"),null);
					users.add(user);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
	
	@Override
	public User getUserById(int id) {
		User user = null;
		ResultSet userRS = gestionUser.getUserById(id);
		
		try {
			while(userRS.next()) {
				
				user = new User(userRS.getString("nom"), userRS.getString("prenom"), userRS.getInt("id"), userRS.getString("role"), null);
			}
		} catch (SQLException e) {
			
			System.out.println("***** erreur lors de chargement de user de getuserbyid dans user man *****");
		}
		
		return user;
	}
	
	@Override
	public Client getClientByCin(String cin) {
		Client client = null;
		ResultSet clientRS = gestionUser.getClientByCin(cin);
		
		try {
			while(clientRS.next()) {
				
				client = new Client(clientRS.getString("nom"), clientRS.getString("prenom"), clientRS.getString("cin"), clientRS.getString("telephone"));
			}
		} catch (SQLException e) {
			
			System.out.println("***** erreur lors de chargement de client de getuserbyid dans user man *****");
		}
		
		return client;
	}
	
	@Override
	public int deleteClient(String cin) {
		return gestionUser.deleteClient(cin);
	}
	
	@Override
	public String getClientCinByProject(int idProject) {
		String cin = null;
		
		ResultSet clientCin = gestionUser.getClientCinByProject(idProject);
		
		try {
			while(clientCin.next()) {
				
				cin = clientCin.getString("cin");
			}
		} catch (SQLException e) {
			
			System.out.println("***** erreur lors de recuperation de client cin de getclientcinbyproject dans user man *****");
		}
		
		return cin;
	}
	
	@Override
	public int updateClient(Client client, String oldClientCin) {
		return gestionUser.updateClient(client, oldClientCin);
	}
	
	@Override
	public List<User> getDeveloppersByTechnologie(Technology technologie) {
		List<User> developpers = new ArrayList<>();
		
		ResultSet resDevelpr = gestionUser.getDeveloppersByTechnologie(technologie);
		try {
			while(resDevelpr.next()) {
				User developper = new User(resDevelpr.getString("nom"),resDevelpr.getString("prenom"),Integer.parseInt(resDevelpr.getString("id")),resDevelpr.getString("role"),null);
				developpers.add(developper);
			}
		} catch (SQLException e) {
			System.out.println("***** erreur lors de recuperation en list des devloppeurs user managment *****");
		}
		
		return developpers;
	}
	
	@Override
	public List<User> getDeveloppersByTechnologies(List<Technology> technologies){
		
		List<User> developpers = new ArrayList<>();
		
		for(Technology technologie: technologies) {
			
			List<User> developpersByTechno = this.getDeveloppersByTechnologie(technologie);
			
			for(User developper: developpersByTechno ) {
				
				boolean exist = false;
				
				for(User existDevelopper: developpers) {
					
					if(existDevelopper.getId() == developper.getId()) {
						exist = true;
						existDevelopper.getTechnologies().add(technologie);
					}
					
				}
				
				if( !exist ) {
					User newDevelopper = new User(developper.getNom(),developper.getPrenom(),developper.getId(),developper.getRole(),null);
					newDevelopper.initTechnologies();
					newDevelopper.getTechnologies().add(technologie);
					developpers.add(newDevelopper);
				}
				
			}
			
		}
		
		return developpers;
	}

	@Override
	public boolean affectDeveloppersToProject(Project project) {
		return gestionUser.affectDeveloppersToProject(project);
	}

	@Override
	public List<User> getDeveloppersByProject(String idProject) {
		List<User> developpers = new ArrayList<>();
		
		ResultSet resDevelpr = gestionUser.getDeveloppersByProject(idProject);
		try {
			while(resDevelpr.next()) {
				User developper = new User(resDevelpr.getString("nom"),resDevelpr.getString("prenom"),Integer.parseInt(resDevelpr.getString("id")),resDevelpr.getString("role"),null);
				developpers.add(developper);
			}
		} catch (SQLException e) {
			System.out.println("***** erreur lors de recuperation en list des devloppeurs by project user managment *****");
		}
		
		return developpers;
	}
	
	@Override
	public int updateDevelopper(User developper) {
		return gestionUser.updateDevelopper(developper);
	}
	
}
