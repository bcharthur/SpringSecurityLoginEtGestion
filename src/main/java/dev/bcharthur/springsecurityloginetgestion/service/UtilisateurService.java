package dev.bcharthur.springsecurityloginetgestion.service;

import dev.bcharthur.springsecurityloginetgestion.model.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UtilisateurService {
    List<Utilisateur> consulterUtilisateurs();

    Utilisateur consulterUtilisateurParId(int no_utilisateur);

    Utilisateur consulterUtilisateurParPseudo(String pseudo);

    void creerUtilisateur(Utilisateur utilisateur);

    void supprimerUtilisateurParId(int idUtilisateur);

    void modifierUtilisateur(Utilisateur utilisateur);

}
