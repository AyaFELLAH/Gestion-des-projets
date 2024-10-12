package businessLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataLayer.GestionTache;
import dataLayer.GestionTacheInterface;
import presentationLayer.models.Service;
import presentationLayer.models.Tache;
import presentationLayer.models.User;

public class TacheManagment implements TacheManagmentInterface{

	private GestionTacheInterface gestionTache;
	
	public TacheManagment() {
		super();
		gestionTache = new GestionTache();
	}



	@Override
	public List<Tache> getTachesByService(int IdService) {
		List<Tache> taches = new ArrayList<Tache>();
		ResultSet tacheRS = gestionTache.getTachesByService(IdService);
		UserManagmentInterface userManagment = new UserManagment();
		
		try {
			while(tacheRS.next()) {
				
				int id = Integer.parseInt(tacheRS.getString("id"));
				String nom = tacheRS.getString("nom");
				String description = tacheRS.getString("description");
				int pourcentage = Integer.parseInt(tacheRS.getString("pourcentage"));
				int idDeveloppeur = Integer.parseInt(tacheRS.getString("idDeveloppeur"));
				
				Tache tache = new Tache(id, nom, description, pourcentage, IdService, idDeveloppeur);
				User developpeur = userManagment.getUserById(idDeveloppeur);
				tache.setDeveloppeur(developpeur);
				
				taches.add(tache);
			}
		} catch (SQLException e) {
			
			System.err.println("***** erreur lors de chargement de taches de get taches by service  dans tache man *****");
		}
		
		return taches;
	}
	
	@Override
	public int addTache(Tache tache) {
		return gestionTache.addTache(tache);
	}
	
	@Override
	public int changeTacheProgress(int idTache, int progress) {
		return gestionTache.changeTacheProgress(idTache, progress);
	}
}
