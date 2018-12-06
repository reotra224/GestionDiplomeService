package gn.traore.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name="comptes")
public class Compte implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String login;
	private String mdp;
	private String statut;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "id_organisation")
	private Organisation organisation;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="comptes_roles",
			joinColumns = {@JoinColumn(name = "login_compte", referencedColumnName = "login")},
            inverseJoinColumns = {@JoinColumn(name = "id_role", referencedColumnName = "idRole")})
	private Collection<Role> roles = new ArrayList<>();
	
	/**
	 * 
	 */
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param login
	 * @param mdp
	 * @param statut
	 * @param roles
	 * @param adminPLTF
	 */
	public Compte(String login, String mdp, String statut, Collection<Role> roles) {
		super();
		this.login = login;
		this.mdp = mdp;
		this.statut = statut;
		this.roles = roles;
	}



	/**
	 * @param login
	 * @param mdp
	 * @param statut
	 * @param organisation
	 * @param adminPLTF
	 */
	public Compte(String login, String mdp, String statut, Organisation organisation) {
		super();
		this.login = login;
		this.mdp = mdp;
		this.statut = statut;
		this.organisation = organisation;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}

	/**
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	/**
	 * @return the statut
	 */
	public String getStatut() {
		return statut;
	}

	/**
	 * @param statut the statut to set
	 */
	public void setStatut(String statut) {
		this.statut = statut;
	}

	/**
	 * @return the organisation
	 */
	public Organisation getOrganisation() {
		return organisation;
	}

	/**
	 * @param organisation the organisation to set
	 */
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	/**
	 * @return the roles
	 */
	public Collection<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
}
