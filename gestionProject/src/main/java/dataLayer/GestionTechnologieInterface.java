package dataLayer;

import java.sql.ResultSet;

import presentationLayer.models.Technology;

public interface GestionTechnologieInterface {
	public ResultSet getTechnologies();
	public ResultSet getTechnologyById(int idTechnology);
	public ResultSet getTechnologiesByDevelopper(int idDevelopper);
	public int addTechnology(Technology technology);
	public ResultSet getTechnologyIdByFields(Technology technology);
	public int addTechnologyToDevelopper(Technology technology);
}
