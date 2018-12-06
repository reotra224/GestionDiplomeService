package gn.traore.demo.entities;

import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@DiscriminatorValue("ETAB")
public class Etablissement extends Organisation {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sigleEtab;
	private String specialite;
	
	@JsonIgnore
	@OneToMany(mappedBy="etablissement", fetch=FetchType.LAZY)
	private Collection<DiplomeSecure> diplomes;
	
	public Etablissement() {
		super();
	}

	/**
	 * @param nom
	 * @param email
	 * @param adresse
	 * @param adminPLTF
	 * @param sigleEtab
	 * @param specialite
	 */
	public Etablissement(String sigleEtab, String nom, String email, String specialite, String adresse, 
			AdminPlateforme adminPLTF) {
		super(nom, email, adresse, adminPLTF);
		this.sigleEtab = sigleEtab;
		this.specialite = specialite;
	}


	/**
	 * @return the sigleEtab
	 */
	public String getSigleEtab() {
		return sigleEtab;
	}

	/**
	 * @param sigleEtab the sigleEtab to set
	 */
	public void setSigleEtab(String sigleEtab) {
		this.sigleEtab = sigleEtab;
	}

	/**
	 * @return the specialite
	 */
	public String getSpecialite() {
		return specialite;
	}

	/**
	 * @param specialite the specialite to set
	 */
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	/**
	 * @return the diplomes
	 */
	public Collection<DiplomeSecure> getDiplomes() {
		return diplomes;
	}

	/**
	 * @param diplomes the diplomes to set
	 */
	public void setDiplomes(Collection<DiplomeSecure> diplomes) {
		this.diplomes = diplomes;
	}	
	
}
