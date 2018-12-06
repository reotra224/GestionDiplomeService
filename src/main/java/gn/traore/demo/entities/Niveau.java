package gn.traore.demo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

@Entity
public class Niveau implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String lib_niveau;
	private String desc_niveau;
	
	@JsonIgnore
	@OneToMany(mappedBy="niveau", fetch=FetchType.LAZY)
	private Collection<DiplomeSecure> diplomes;
	/**
	 * 
	 */
	public Niveau() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param lib_niveau
	 * @param desc_niveau
	 */
	public Niveau(String lib_niveau, String desc_niveau) {
		super();
		this.lib_niveau = lib_niveau;
		this.desc_niveau = desc_niveau;
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
	 * @return the lib_niveau
	 */
	public String getLib_niveau() {
		return lib_niveau;
	}
	/**
	 * @param lib_niveau the lib_niveau to set
	 */
	public void setLib_niveau(String lib_niveau) {
		this.lib_niveau = lib_niveau;
	}
	/**
	 * @return the desc_niveau
	 */
	public String getDesc_niveau() {
		return desc_niveau;
	}
	/**
	 * @param desc_niveau the desc_niveau to set
	 */
	public void setDesc_niveau(String desc_niveau) {
		this.desc_niveau = desc_niveau;
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
