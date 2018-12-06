package gn.traore.demo.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity(name="roles")
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long idRole;
	private String nomRole;
	private String descRole;
	
	/**
	 * 
	 */
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param nomRole
	 * @param descRole
	 */
	public Role(String nomRole, String descRole) {
		super();
		this.nomRole = nomRole;
		this.descRole = descRole;
	}
	/**
	 * @return the nomRole
	 */
	public String getNomRole() {
		return nomRole;
	}
	/**
	 * @param nomRole the nomRole to set
	 */
	public void setNomRole(String nomRole) {
		this.nomRole = nomRole;
	}
	/**
	 * @return the descRole
	 */
	public String getDescRole() {
		return descRole;
	}
	/**
	 * @param descRole the descRole to set
	 */
	public void setDescRole(String descRole) {
		this.descRole = descRole;
	}
	

}
