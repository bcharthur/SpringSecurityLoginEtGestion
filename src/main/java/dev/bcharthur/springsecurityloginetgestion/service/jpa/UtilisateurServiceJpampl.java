package dev.bcharthur.springsecurityloginetgestion.service.jpa;

import dev.bcharthur.springsecurityloginetgestion.service.UtilisateurService;
import dev.bcharthur.springsecurityloginetgestion.model.*;

import dev.bcharthur.springsecurityloginetgestion.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceJpampl implements UtilisateurService {
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    PasswordEncoder encodeur;

    @Override
    public List<Utilisateur> consulterUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public void creerUtilisateur(Utilisateur utilisateur) {

        // encodage du mot de passe
        utilisateur.setMot_de_passe(encodeur.encode(utilisateur.getMot_de_passe()));

//        utilisateurRepository.save(utilisateur);
        // insertion en base
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public void supprimerUtilisateurParId(int no_utilisateur) {
        // on vérifie d'abord que l'utilisateur existe
        if (utilisateurRepository.existsById(no_utilisateur)){
            utilisateurRepository.deleteById(no_utilisateur);
        }
        // si ca n'est pas le cas => on lance une exception
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "l'utilisateur n'existe pas");
        }
    }

    @Override
    public Utilisateur consulterUtilisateurParId(int no_utilisateur) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(no_utilisateur);
        if (utilisateurOptional.isPresent()) {
            return utilisateurOptional.get();
        } else {
            // Gérer le cas où aucun utilisateur avec l'ID spécifié n'est trouvé
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé avec l'ID spécifié");
        }
    }


    @Override
    public Utilisateur consulterUtilisateurParPseudo(String pseudo) {
        return utilisateurRepository.findByPseudo(pseudo);
    }


    @Override
    public void modifierUtilisateur(Utilisateur utilisateur) {
        // on vérifie d'abord que l'utilisateur existe
        if (utilisateurRepository.existsById(utilisateur.getNo_utilisateur())) {
            utilisateurRepository.save(utilisateur);
        }
        // si ce n'est pas le cas, on lance une exception
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'utilisateur n'existe pas");
        }
    }


    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

//
//    @Override
//    public List<Utilisateur> rechercherUtilisateurs(ParametresRecherche parametresRecherche) {
//        return List.of();
//    }
//
}
