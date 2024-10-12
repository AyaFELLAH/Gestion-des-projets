package presentationLayer.models;

import java.sql.Date;
import java.util.List;



public class Project {
	private String name,description,methodologie;
	private int nombreJours,id;
	private Date dateDemarrage,datelivraison,dateReunion;
	private User chef;
	private Client client;
	private List<Technology> technologies;
	private List<User> developpeurs;
	
	public List<User> getDeveloppeurs() {
		return developpeurs;
	}

	public void setDeveloppeurs(List<User> developpeurs) {
		this.developpeurs = developpeurs;
	}

	public Project() {
	}
	
	public Project(String name, String description, int nombreJours, Date dateDemarrage,
			Date datelivraison, User chef, Client client) {
		super();
		this.name = name;
		this.description = description;
		this.nombreJours = nombreJours;
		this.dateDemarrage = dateDemarrage;
		this.datelivraison = datelivraison;
		this.chef = chef;
		this.client = client;
	}
	
	public Project(int id, String name, String description, int nombreJours, Date dateDemarrage,
			Date datelivraison, User chef, Client client) {
		this(name,description,nombreJours,dateDemarrage,datelivraison,chef,client);
		this.id = id;
	}	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNombreJours() {
		return nombreJours;
	}
	public void setNombreJours(int nombreJours) {
		this.nombreJours = nombreJours;
	}
	public Date getDateDemarrage() {
		return dateDemarrage;
	}
	public void setDateDemarrage(Date dateDemarrage) {
		this.dateDemarrage = dateDemarrage;
	}
	public Date getDatelivraison() {
		return datelivraison;
	}
	public void setDatelivraison(Date datelivraison) {
		this.datelivraison = datelivraison;
	}
	

	public User getChef() {
		return chef;
	}

	public void setChef(User chef) {
		this.chef = chef;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getMethodologie() {
		return methodologie;
	}

	public void setMethodologie(String methodologie) {
		this.methodologie = methodologie;
	}

	public List<Technology> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<Technology> technologies) {
		this.technologies = technologies;
	}

	public Date getDateReunion() {
		return dateReunion;
	}

	public void setDateReunion(Date dateReunion) {
		this.dateReunion = dateReunion;
	}
}
