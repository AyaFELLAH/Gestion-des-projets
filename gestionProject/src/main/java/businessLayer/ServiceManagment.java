package businessLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataLayer.GestionService;
import presentationLayer.models.Service;
import presentationLayer.models.Tache;
import presentationLayer.models.User;

public class ServiceManagment implements ServiceManagmentInterface {
	private GestionService gestionService;
	
	
	
	public ServiceManagment() {
		super();
		gestionService = new GestionService();
	}

	@Override
	public int addService(Service service) {
		if(gestionService.addService(service) == 1) {
			int idService = this.getIdService(service);
			TacheManagmentInterface tacheManagment = new TacheManagment();
			
			for(Tache tache : service.getTaches()) {
				tache.setIdService(idService);
				tacheManagment.addTache(tache);
			}
			
			return 1;
		}
		return 0;
	}

	@Override
	public List<Service> getServicesByProject(int idProject) {
		List<Service> services = new ArrayList<Service>();
		ResultSet serviceRS = gestionService.getServicesByProject(idProject);
		TacheManagmentInterface tacheManagment = new TacheManagment();
		
		try {
			while(serviceRS.next()) {
				
				Service service = new Service(Integer.parseInt(serviceRS.getString("id")), serviceRS.getString("nom"), serviceRS.getString("description"), Integer.parseInt(serviceRS.getString("duree")), idProject); 
				List<Tache> taches = tacheManagment.getTachesByService(service.getId());
				service.setTaches(taches);
				
				services.add(service);
			}
		} catch (SQLException e) {
			
			System.err.println("***** erreur lors de chargement de services de getservicebyid project dans service man *****");
		}
		
		return services;
	}
	
	@Override
	public int getIdService(Service service) {
		int idService = -1;
		ResultSet idServiceRS = gestionService.getIdService(service);
		
		try {
			while(idServiceRS.next()) {
				
				idService = idServiceRS.getInt("id");
				
			}
		} catch (SQLException e) {
			
			System.err.println("***** erreur lors de chargement de id service de get id service project dans service man *****");
		}
		
		return idService;
	}

}
