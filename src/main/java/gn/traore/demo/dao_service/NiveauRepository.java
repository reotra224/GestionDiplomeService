package gn.traore.demo.dao_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import gn.traore.demo.entities.*;

@RepositoryRestResource
public interface NiveauRepository extends JpaRepository<Niveau, Long> {
	
	@Query("SELECT n FROM Niveau n WHERE n.lib_niveau=:lib")
	public Niveau findNiveau(@Param("lib")String libelle);

	
	
	
	
	
	
	
	

}
