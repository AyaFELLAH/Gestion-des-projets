package dataLayer;

import java.sql.ResultSet;

import presentationLayer.models.Service;

public interface GestionServiceInterface {
	public int addService(Service service);
	public ResultSet getServicesByProject(int idProject);
	public ResultSet getIdService(Service service);
}
