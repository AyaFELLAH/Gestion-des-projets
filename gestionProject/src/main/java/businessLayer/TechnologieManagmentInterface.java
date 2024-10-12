package businessLayer;

import java.sql.ResultSet;
import java.util.List;

import presentationLayer.models.Technology;

public interface TechnologieManagmentInterface {

	public List<Technology> getTechnologies();
	public Technology getTechnologyById(int idTechnology);
	public List<Technology> getTechnologiesByDevelopper(int idDevelopper);
	public int getTechnologyIdByFields(Technology technology);
	public int addTechnology(Technology technology);
	public int addTechnologyToDevelopper(Technology technology);
}
