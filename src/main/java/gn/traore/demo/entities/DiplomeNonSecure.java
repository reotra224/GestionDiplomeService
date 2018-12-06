package gn.traore.demo.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DiplomeNonSecure implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String matriculeEtudiant;
	private String nomEtudiant;
	private String prenomEtudiant;
	private String photoEtudiant;
	/**
	 * @return the matriculeEtudiant
	 */
	public String getMatriculeEtudiant() {
		return matriculeEtudiant;
	}
	/**
	 * @param matriculeEtudiant the matriculeEtudiant to set
	 */
	public void setMatriculeEtudiant(String matriculeEtudiant) {
		this.matriculeEtudiant = matriculeEtudiant;
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

}
