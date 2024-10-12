package businessLayer;

import java.util.List;

import presentationLayer.models.Tache;

public interface TacheManagmentInterface {
	
	public List<Tache> getTachesByService(int IdService);
	public int addTache(Tache tache);
	public int changeTacheProgress(int idTache, int progress);
}
