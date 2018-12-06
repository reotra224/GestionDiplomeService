package gn.traore.demo.entities;

public class InfosDiplome {
	
	private int idEtab;
	private String filiere;
	private String niveau;
	private int promotion;
	
	/**
	 * 
	 */
	public InfosDiplome() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param idEtab
	 * @param filiere
	 * @param niveau
	 * @param promotion
	 */
	public InfosDiplome(String filiere, String niveau, int promotion) {
		super();
		this.filiere = filiere;
		this.niveau = niveau;
		this.promotion = promotion;
	}

	/**
	 * @return the idEtab
	 */
	public int getIdEtab() {
		return idEtab;
	}
	/**
	 * @param idEtab the idEtab to set
	 */
	public void setIdEtab(int idEtab) {
		this.idEtab = idEtab;
	}
	/**
	 * @return the filiere
	 */
	public String getFiliere() {
		return filiere;
	}
	/**
	 * @param filiere the filiere to set
	 */
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	/**
	 * @return the niveau
	 */
	public String getNiveau() {
		return niveau;
	}
	/**
	 * @param niveau the niveau to set
	 */
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	/**
	 * @return the promotion
	 */
	public int getPromotion() {
		return promotion;
	}
	/**
	 * @param promotion the promotion to set
	 */
	public void setPromotion(int promotion) {
		this.promotion = promotion;
	}
	

}
