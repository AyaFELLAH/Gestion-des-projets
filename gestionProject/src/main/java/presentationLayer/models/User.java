package presentationLayer.models;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String nom,prenom,role;
	private int id;
	private Account account;
	private List<Technology> technologies;

	public User() {
		
	}
	
	public User(String nom, String prenom, int id, String role, Account account) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.id = id;
		this.role = role;
		this.account = account;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Technology> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<Technology> technologies) {
		this.technologies = technologies;
	}
	
	public void initTechnologies() {
		technologies = new ArrayList<Technology>();
	}
}
