package presentationLayer.models;

import java.sql.Date;

public class Notification {
	
	private int id, idEmetteur, idRecepteur;
	private String message; 
	private Date dateEnvoi;
	private User emetteur;
	
	public Notification() {
		super();
	}
	public Notification(int id, int idEmetteur, int idRecepteur, String message, Date dateEnvoi) {
		super();
		this.id = id;
		this.idEmetteur = idEmetteur;
		this.idRecepteur = idRecepteur;
		this.message = message;
		this.dateEnvoi = dateEnvoi;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdEmetteur() {
		return idEmetteur;
	}
	public void setIdEmetteur(int idEmetteur) {
		this.idEmetteur = idEmetteur;
	}
	public int getIdRecepteur() {
		return idRecepteur;
	}
	public void setIdRecepteur(int idRecepteur) {
		this.idRecepteur = idRecepteur;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDateEnvoi() {
		return dateEnvoi;
	}
	public void setDateEnvoi(Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}
	public User getEmetteur() {
		return emetteur;
	}
	public void setEmetteur(User emetteur) {
		this.emetteur = emetteur;
	}
	
}
