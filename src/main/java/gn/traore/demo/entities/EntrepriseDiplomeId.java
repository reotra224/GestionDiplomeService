package gn.traore.demo.entities;

import java.io.Serializable;

public class EntrepriseDiplomeId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long entreprise;
	private Long diplome;
	/**
	 * @return the entreprise
	 */
	public Long getEntreprise() {
		return entreprise;
	}
	/**
	 * @param entreprise the entreprise to set
	 */
	public void setEntreprise(Long entreprise) {
		this.entreprise = entreprise;
	}
	/**
	 * @return the diplome
	 */
	public Long getDiplome() {
		return diplome;
	}
	/**
	 * @param diplome the diplome to set
	 */
	public void setDiplome(Long diplome) {
		this.diplome = diplome;
	}

}
