package dev.bcharthur.springsecurityloginetgestion;

import dev.bcharthur.springsecurityloginetgestion.service.UtilisateurService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringSecurityLoginEtGestionApplication {

    @Autowired
    public UtilisateurService service;



    @PostConstruct
    public void init() {


//        service.creerUtilisateur(new Utilisateur("test1234","admin","admin","admin","admin","admin","admin","admin","admin",9999,"ADMIN"));
//        service.creerUtilisateur(new Utilisateur("user","user","user","user","user","user","user","user","user",1,"USER"));
//        service.creerUtilisateur(new Utilisateur("jessy","jessy","Toutfaire","Jessy","ADMIN,USER"));
//        service.creerUtilisateur(new Utilisateur("jeffrey","jeffrey","Leminimum","Jeffrey","USER"));
//        service.creerUtilisateur(new Utilisateur("jean","jean","Cérien","Jean","ADMIN"));
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityLoginEtGestionApplication.class, args);
    }

}
