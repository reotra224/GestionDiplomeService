package gn.traore.demo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

@Entity
public class DiplomeSecure implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long idDiplome;
	private String empreinte;
	private String filiere;
	private int promotion;
	private String codeQR;
	private String nomEtudiant;
	private String prenomEtudiant;
	private String photoEtudiant;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_etablissement")
	private Etablissement etablissement;
	
	@JsonIgnore
	@OneToMany(mappedBy="diplome", fetch=FetchType.LAZY)
	private Collection<EntrepiseAuthDiplome> diplomesAuth;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_niveau")
	private Niveau niveau;

	public DiplomeSecure() {
		super();
	}

	/**
	 * @param empreinte
	 * @param filiere
	 * @param promotion
	 * @param codeQR
	 * @param nomEtudiant
	 * @param prenomEtudiant
	 * @param photoEtudiant
	 * @param etablissement
	 * @param niveau
	 */
	public DiplomeSecure(String empreinte, String filiere, int promotion, String codeQR, String nomEtudiant,
			String prenomEtudiant, String photoEtudiant, Etablissement etablissement, Niveau niveau) {
		super();
		this.empreinte = empreinte;
		this.filiere = filiere;
		this.promotion = promotion;
		this.codeQR = codeQR;
		this.nomEtudiant = nomEtudiant;
		this.prenomEtudiant = prenomEtudiant;
		this.photoEtudiant = photoEtudiant;
		this.etablissement = etablissement;
		this.niveau = niveau;
	}
	
	

	/**
	 * @param filiere
	 * @param promotion
	 * @param nomEtudiant
	 * @param prenomEtudiant
	 * @param photoEtudiant
	 * @param etablissement
	 * @param niveau
	 */
	public DiplomeSecure(String filiere, int promotion, String nomEtudiant, String prenomEtudiant, String photoEtudiant,
			Etablissement etablissement, Niveau niveau) {
		super();
		this.filiere = filiere;
		this.promotion = promotion;
		this.nomEtudiant = nomEtudiant;
		this.prenomEtudiant = prenomEtudiant;
		this.photoEtudiant = photoEtudiant;
		this.etablissement = etablissement;
		this.niveau = niveau;
	}

	/**
	 * @param filiere
	 * @param promotion
	 * @param nomEtudiant
	 * @param prenomEtudiant
	 * @param photoEtudiant
	 * @param niveau
	 */
	public DiplomeSecure(String filiere, int promotion, String nomEtudiant, String prenomEtudiant, String photoEtudiant,
			Niveau niveau) {
		super();
		this.filiere = filiere;
		this.promotion = promotion;
		this.nomEtudiant = nomEtudiant;
		this.prenomEtudiant = prenomEtudiant;
		this.photoEtudiant = photoEtudiant;
		this.niveau = niveau;
	}	

	/**
	 * @param filiere
	 * @param promotion
	 * @param nomEtudiant
	 * @param prenomEtudiant
	 * @param photoEtudiant
	 */
	public DiplomeSecure(String filiere, int promotion, String nomEtudiant, String prenomEtudiant, String photoEtudiant) {
		super();
		this.filiere = filiere;
		this.promotion = promotion;
		this.nomEtudiant = nomEtudiant;
		this.prenomEtudiant = prenomEtudiant;
		this.photoEtudiant = photoEtudiant;
	}

	/**
	 * @return the idDiplome
	 */
	public Long getIdDiplome() {
		return idDiplome;
	}

	/**
	 * @param idDiplome the idDiplome to set
	 */
	public void setIdDiplome(Long idDiplome) {
		this.idDiplome = idDiplome;
	}

	/**
	 * @return the empreinte
	 */
	public String getEmpreinte() {
		return empreinte;
	}

	/**
	 * @param empreinte the empreinte to set
	 */
	public void setEmpreinte(String empreinte) {
		this.empreinte = empreinte;
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

	/**
	 * @return the codeQR
	 */
	public String getCodeQR() {
		return codeQR;
	}

	/**
	 * @param codeQR the codeQR to set
	 */
	public void setCodeQR(String codeQR) {
		this.codeQR = codeQR;
	}

	/**
	 * @return the nomEtudiant
	 */
	public String getNomEtudiant() {
		return nomEtudiant;
	}

	/**
	 * @param nomEtudiant the nomEtudiant to set
	 */
	public void setNomEtudiant(String nomEtudiant) {
		this.nomEtudiant = nomEtudiant;
	}

	/**
	 * @return the prenomEtudiant
	 */
	public String getPrenomEtudiant() {
		return prenomEtudiant;
	}

	/**
	 * @param prenomEtudiant the prenomEtudiant to set
	 */
	public void setPrenomEtudiant(String prenomEtudiant) {
		this.prenomEtudiant = prenomEtudiant;
	}

	/**
	 * @return the photoEtudiant
	 */
	public String getPhotoEtudiant() {
		return photoEtudiant;
	}

	/**
	 * @param photoEtudiant the photoEtudiant to set
	 */
	public void setPhotoEtudiant(String photoEtudiant) {
		this.photoEtudiant = photoEtudiant;
	}

	/**
	 * @return the etablissement
	 */
	public Etablissement getEtablissement() {
		return etablissement;
	}

	/**
	 * @param etablissement the etablissement to set
	 */
	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

	/**
	 * @return the niveau
	 */
	public Niveau getNiveau() {
		return niveau;
	}

	/**
	 * @param niveau the niveau to set
	 */
	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}
	
}
