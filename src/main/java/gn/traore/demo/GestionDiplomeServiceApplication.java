package gn.traore.demo;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import gn.traore.demo.dao_service.AdminRepository;
import gn.traore.demo.dao_service.CompteRepository;
import gn.traore.demo.dao_service.DiplomeRepository;
import gn.traore.demo.dao_service.EntrepriseRepository;
import gn.traore.demo.dao_service.EtablissementRepository;
import gn.traore.demo.dao_service.NiveauRepository;
import gn.traore.demo.dao_service.RoleRepository;
import gn.traore.demo.entities.*;

@EnableEurekaClient
@SpringBootApplication
public class GestionDiplomeServiceApplication implements CommandLineRunner {
	
	@Autowired
	private CompteRepository daoCompte;
	@Autowired
	private EntrepriseRepository daoEntreprise;
	@Autowired
	private AdminRepository daoAdmin;
	@Autowired
	private DiplomeRepository daoDiplome;
	@Autowired
	private EtablissementRepository daoEtablissemnt;
	@Autowired
	private NiveauRepository daoNiveau;
	@Autowired
	private RoleRepository daoRole;
	public static void main(String[] args) {
		SpringApplication.run(GestionDiplomeServiceApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		AdminPlateforme u1 = daoAdmin.save(new AdminPlateforme("NOUR", "Abdoulaye", "nour@gmail.com", "774408602"));
		Etablissement o1 =  daoEtablissemnt.save(new Etablissement("ESTM", "Ecole Sup Tech et de Manag", "contact@estm.sn", "Informatique", "En face Hopital FANN", u1));
		Entreprise o2 = daoEntreprise.save(new Entreprise("DakarApps", "Informatique", "contact@dakarapps.com", "Dieuppeul Derklé", u1));
		
		Compte c1 = new Compte("nour", "passer123", "ACTIVER", o1);
		Role r1 = daoRole.save(new Role("ETABLISSEMENT", "Role pour les admins d'établissement."));
		c1.getRoles().add(r1);
		c1 = daoCompte.save(c1);
		
		Compte c2 = new Compte("traore", "passer123", "ACTIVER", o2);
		Role r2 = daoRole.save(new Role("ENTREPRISE", "Role pour les admins des entreprises."));
		c2.getRoles().add(r2);
		c2 = daoCompte.save(c2);
		
		//Ajout d'un diplome
		Niveau n1 = daoNiveau.save(new Niveau("Licence", "Licence du LMD"));
		Niveau n2 = daoNiveau.save(new Niveau("Master", "Master du LMD"));
		DiplomeSecure d1 = new DiplomeSecure("RT", 2018, "KANDE", "Namorigbè", "kande.jpg", o1, n2);
		DiplomeSecure d2 = new DiplomeSecure("GLAR", 2018, "DIOP", "Ibrahima", "diop.jpg", o1, n2);
		DiplomeSecure d3 = new DiplomeSecure("RT", 2018, "TRAORE", "Billy", "billy.jpg", o1, n2);
		
		Stream.of(d1,d2,d3).forEach(d->daoDiplome.save(d));
		daoDiplome.findAll().forEach(d->System.out.println(d.getNiveau().getLib_niveau()));
		
		/*List<Compte> comptes = daoCompte.findAll();
		for (Compte cpt : comptes) {
			System.out.println(cpt.getLogin());
		}
		
		//roles.forEach(System.out::println);
		*/
	}
}
