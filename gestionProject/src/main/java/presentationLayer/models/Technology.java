package presentationLayer.models;

public class Technology {
	private int id;
	private String nom,description;
	private int idDevelopper;
	
	public Technology() {
		super();
	}

	public Technology(int id, String nom, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getIdDevelopper() {
		return idDevelopper;
	}

	public void setIdDevelopper(int idDevelopper) {
		this.idDevelopper = idDevelopper;
	}
	
}
