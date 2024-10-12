package presentationLayer.models;

import java.util.List;

public class Service {
	private int id,idProject,duree;
	private String nom,description;
	private List<Tache> taches;
	private Project projet;
	
	public Service() {
		super();
	}
	
	public Service(int id, String nom, String description, int duree, Project projet) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.duree = duree;
		this.projet = projet;
	}

	public Service(int id, String nom, String description, int duree, int idProject) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.duree = duree;
		this.idProject = idProject;
	}

	public Service(String nom, String description, int duree, int idProject) {
		super();
		this.nom = nom;
		this.description = description;
		this.duree = duree;
		this.idProject = idProject;
	}

	public Service(String nom, String description, int duree) {
		super();
		this.nom = nom;
		this.description = description;
		this.duree = duree;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

	public Project getProjet() {
		return projet;
	}

	public void setProjet(Project projet) {
		this.projet = projet;
	}
	
	
	
}
