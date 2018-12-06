package gn.traore.demo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AdminPlateforme implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long id;
	private String nom;
	private String prenom;
	private String email;
	private String tel;
	
	@JsonIgnore
	@OneToMany(mappedBy="adminPLTF", fetch=FetchType.LAZY)
	private Collection<Organisation> organisation;
	/**
	 * 
	 */
	public AdminPlateforme() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param tel
	 */
	public AdminPlateforme(String nom, String prenom, String email, String tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
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
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return the organisation
	 */
	public Collection<Organisation> getOrganisation() {
		return organisation;
	}
	/**
	 * @param organisation the organisation to set
	 */
	public void setOrganisation(Collection<Organisation> organisation) {
		this.organisation = organisation;
	}
	
}
