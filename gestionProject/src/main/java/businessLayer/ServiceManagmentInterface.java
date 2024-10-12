package businessLayer;

import java.util.List;

import presentationLayer.models.Service;

public interface ServiceManagmentInterface {
	public int addService(Service service);
	public List<Service> getServicesByProject(int idProject);
	public int getIdService(Service service);
}
