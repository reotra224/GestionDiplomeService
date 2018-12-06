package gn.traore.demo.dao_service;

import org.springframework.data.jpa.repository.JpaRepository;/*
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;*/
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.ResponseBody;

import gn.traore.demo.entities.DiplomeSecure;

@RepositoryRestResource
public interface DiplomeRepository extends JpaRepository<DiplomeSecure, Long> {
	
	/*@Query("SELECT d FROM DiplomeSecurise d WHERE d.empreinte=:empreinte")
	public DiplomeSecurise findDiplome(@Param("empreinte")String empreinte);*/

}
