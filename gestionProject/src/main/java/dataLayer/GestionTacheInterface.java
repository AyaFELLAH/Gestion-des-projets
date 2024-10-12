package dataLayer;

import java.sql.ResultSet;

import presentationLayer.models.Tache;

public interface GestionTacheInterface {
	
	public ResultSet getTachesByService(int idService);
	public int addTache(Tache tache);
	public int changeTacheProgress(int idTache, int progress);
	
}
