package presentationLayer.models;

public class Tache {
	private int id,pourcentage,idService,idDeveloppeur;
	private String nom,description;
	private User developpeur;
	private Service service;
	private User user;
	
	public Tache() {
		super();
	}

	public Tache(int id, String nom, String description, int pourcentage, int idService, int idDeveloppeur) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.pourcentage = pourcentage;
		this.idService = idService;
		this.idDeveloppeur = idDeveloppeur;
	}
	
	public Tache(int id, String nom, String description, int pourcentage, Service service, User user) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.pourcentage = pourcentage;
		this.service = service;
		this.user = user;
	}

	public Tache(String nom, String description, int pourcentage, int idService, int idDeveloppeur) {
		super();
		this.nom = nom;
		this.description = description;
		this.pourcentage = pourcentage;
		this.idService = idService;
		this.idDeveloppeur = idDeveloppeur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}

	public int getIdService() {
		return idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}

	public int getIdDeveloppeur() {
		return idDeveloppeur;
	}

	public void setIdDeveloppeur(int idDeveloppeur) {
		this.idDeveloppeur = idDeveloppeur;
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

	public User getDeveloppeur() {
		return developpeur;
	}

	public void setDeveloppeur(User developpeur) {
		this.developpeur = developpeur;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
