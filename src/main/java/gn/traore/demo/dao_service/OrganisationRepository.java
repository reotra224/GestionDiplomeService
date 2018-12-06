package gn.traore.demo.dao_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import gn.traore.demo.entities.*;

@RepositoryRestResource
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {
	
	/*@Query("SELECT o FROM Organisation o WHERE o.type_org=:type")
	public Organisation findOrganisation(@Param("type")String type);*/
}
