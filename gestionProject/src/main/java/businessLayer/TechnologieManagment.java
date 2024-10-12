package businessLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataLayer.GestionTechnologie;
import dataLayer.GestionTechnologieInterface;
import presentationLayer.models.Project;
import presentationLayer.models.Technology;

public class TechnologieManagment implements TechnologieManagmentInterface {

	private GestionTechnologieInterface gestionTechnologie;
	
	public TechnologieManagment() {
		super();
		gestionTechnologie = new GestionTechnologie();
	}
	
	@Override
	public List<Technology> getTechnologies() {
		List<Technology> techs = new ArrayList<>();
		
		ResultSet techsBD = gestionTechnologie.getTechnologies();
		try {
			while(techsBD.next()) {
				
				Technology tech = new Technology(techsBD.getInt("id"), techsBD.getString("nom"), techsBD.getString("description"));
				techs.add(tech);
			}
			
		} catch (SQLException e) {
			
			System.out.println("***** erreur de remplissage des tech dans gettech of tech mang *****");
		}
		return techs;
	}
	
	@Override
	public Technology getTechnologyById(int idTechnology) {
		
		Technology tech = null; 
		
		ResultSet techBD = gestionTechnologie.getTechnologyById(idTechnology);
		try {
			while(techBD.next()) {
				
				tech = new Technology(techBD.getInt("id"), techBD.getString("nom"), techBD.getString("description"));
			}
			
		} catch (SQLException e) {
			
			System.out.println("***** erreur de remplissage de tech dans gettechbyid of tech mang *****");
		}
		return tech;
		
	}
	
	@Override
	public List<Technology> getTechnologiesByDevelopper(int idDevelopper) {
		List<Technology> techs = new ArrayList<>();
		
		ResultSet techsBD = gestionTechnologie.getTechnologiesByDevelopper(idDevelopper);
		try {
			while(techsBD.next()) {
				
				Technology tech = new Technology(techsBD.getInt("id"), techsBD.getString("nom"), techsBD.getString("description"));
				techs.add(tech);
			}
			
		} catch (SQLException e) {
			
			System.out.println("***** erreur de remplissage des tech dans gettech of tech mang *****");
		}
		return techs;
	}
	
	@Override
	public int getTechnologyIdByFields(Technology technology) {
		int idTechnology = -1;
		ResultSet idTechnologyRS = gestionTechnologie.getTechnologyIdByFields(technology);
		
		try {
			while(idTechnologyRS.next()) {
				
				idTechnology = idTechnologyRS.getInt("id");
				
			}
		} catch (SQLException e) {
			
			System.err.println("***** erreur lors de chargement de id technology de dans techno managment *****");
		}
		
		return idTechnology;
	}
	
	@Override
	public int addTechnology(Technology technology) {
		if(gestionTechnologie.addTechnology(technology) == 1) {
			int idTechnologie = this.getTechnologyIdByFields(technology);
			technology.setId(idTechnologie);
			
			if(this.addTechnologyToDevelopper(technology)==1) 	
				return 1;
			return 0;
		}
		
		return 0;
	}
	
	@Override
	public int addTechnologyToDevelopper(Technology technology) {
		return gestionTechnologie.addTechnologyToDevelopper(technology);
	}
}
