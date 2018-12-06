package gn.traore.demo.entities;

import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@DiscriminatorValue("ETRP")
public class Entreprise extends Organisation {
	
	private static final long serialVersionUID = 1L;

	private String domaine;
	
	@JsonIgnore
	@OneToMany(mappedBy="entreprise", fetch=FetchType.LAZY)
	private Collection<EntrepiseAuthDiplome> diplomesAuth;
	
	/**
	 * 
	 */
	public Entreprise() {
		super();
	}
	

	/**
	 * @param nom
	 * @param email
	 * @param adresse
	 * @param adminPLTF
	 * @param domaine
	 */
	public Entreprise(String nom, String domaine, String email, String adresse, AdminPlateforme adminPLTF) {
		super(nom, email, adresse, adminPLTF);
		this.domaine = domaine;
	}


	/**
	 * @return the domaine
	 */
	public String getDomaine() {
		return domaine;
	}

	/**
	 * @param domaine the domaine to set
	 */
	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}


	/**
	 * @return the diplomesAuth
	 */
	public Collection<EntrepiseAuthDiplome> getDiplomesAuth() {
		return diplomesAuth;
	}


	/**
	 * @param diplomesAuth the diplomesAuth to set
	 */
	public void setDiplomesAuth(Collection<EntrepiseAuthDiplome> diplomesAuth) {
		this.diplomesAuth = diplomesAuth;
	}
	
	
}
