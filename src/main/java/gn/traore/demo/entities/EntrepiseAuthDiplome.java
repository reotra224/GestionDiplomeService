package gn.traore.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "AuthentifierDiplome")
@IdClass(EntrepriseDiplomeId.class)
public class EntrepiseAuthDiplome implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id_entreprise", referencedColumnName = "id")
	private Entreprise entreprise;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id_diplome", referencedColumnName = "idDiplome")
	private DiplomeSecure diplome;
	
	@JoinColumn(name="date_auth")
	private Date dateAuthentification;

	/**
	 * @return the entreprise
	 */
	public Entreprise getEntreprise() {
		return entreprise;
	}

	/**
	 * @param entreprise the entreprise to set
	 */
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	/**
	 * @return the diplome
	 */
	public DiplomeSecure getDiplome() {
		return diplome;
	}

	/**
	 * @param diplome the diplome to set
	 */
	public void setDiplome(DiplomeSecure diplome) {
		this.diplome = diplome;
	}

	/**
	 * @return the dateAuthentification
	 */
	public Date getDateAuthentification() {
		return dateAuthentification;
	}

	/**
	 * @param dateAuthentification the dateAuthentification to set
	 */
	public void setDateAuthentification(Date dateAuthentification) {
		this.dateAuthentification = dateAuthentification;
	}

}
