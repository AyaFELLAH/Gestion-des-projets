package dataLayer;

import java.sql.Connection;
import java.sql.SQLException;

public class Connexion {
	private String url, pilote, user, password, dataBase;
	private Connection connection;
	
	public Connexion() {
		this.user = "root";
		this.password = "";
		this.dataBase = "gestionprojets";
		this.url = "jdbc:mysql://127.0.0.1/";
		this.pilote = new String("com.mysql.cj.jdbc.Driver");
		
		try {
			Class.forName(pilote);
			System.out.println("***** chargement de pilote avec succès *****");
		} catch (ClassNotFoundException e) {
			System.out.println("***** Erreur de chargement de pilote *****");
		}	
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void connect() {
		try {
			
			connection=java.sql.DriverManager.getConnection(this.url+this.dataBase, user, password);
			System.out.println("***** bien connecté à la base de données *****");
			
		} catch (SQLException e) {
			System.out.println("***** echec de connexion avec la base de données *****");
		}
	}
	
	public void disconnect()
	{
		try {
			
			connection.close();
			System.out.println("***** bien deconnecté *****");
			
		} catch (SQLException e) {
			System.out.println("***** Erreur lors de deconnexion de la base de données *****");
		}
	}
}
