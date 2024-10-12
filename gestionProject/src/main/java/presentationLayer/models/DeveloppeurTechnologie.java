package presentationLayer.models;

public class DeveloppeurTechnologie {
	private int idDeveloppeur;
	private int idTechnologie;
	
	public DeveloppeurTechnologie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeveloppeurTechnologie(int idDeveloppeur, int idTechnologie) {
		super();
		this.idDeveloppeur = idDeveloppeur;
		this.idTechnologie = idTechnologie;
	}

	public int getIdDeveloppeur() {
		return idDeveloppeur;
	}

	public void setIdDeveloppeur(int idDeveloppeur) {
		this.idDeveloppeur = idDeveloppeur;
	}

	public int getIdTechnologie() {
		return idTechnologie;
	}

	public void setIdTechnologie(int idTechnologie) {
		this.idTechnologie = idTechnologie;
	}
	
	
}
