package gn.traore.demo.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_ORG", discriminatorType=DiscriminatorType.STRING, length=4)
public abstract class Organisation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long id;
	private String nom;
	private String email;
	private String adresse;

	@OneToOne(mappedBy = "organisation")
	private Compte compte;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_admin_PLTF")
	private AdminPlateforme adminPLTF;
	
	/**
	 * 
	 */
	public Organisation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param nom
	 * @param email
	 * @param adresse
	 */
	public Organisation(String nom, String email, String adresse, AdminPlateforme adminPLTF) {
		super();
		this.nom = nom;
		this.email = email;
		this.adresse = adresse;
		this.adminPLTF = adminPLTF;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the compte
	 */
	public Compte getCompte() {
		return compte;
	}

	/**
	 * @param compte the compte to set
	 */
	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	/**
	 * @return the adminPLTF
	 */
	public AdminPlateforme getAdminPLTF() {
		return adminPLTF;
	}

	/**
	 * @param adminPLTF the adminPLTF to set
	 */
	public void setAdminPLTF(AdminPlateforme adminPLTF) {
		this.adminPLTF = adminPLTF;
	}	
	
}
