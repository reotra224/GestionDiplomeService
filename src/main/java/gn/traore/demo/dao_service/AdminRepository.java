package gn.traore.demo.dao_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import gn.traore.demo.entities.AdminPlateforme;

@RepositoryRestResource
public interface AdminRepository extends JpaRepository<AdminPlateforme, Long> {

}
