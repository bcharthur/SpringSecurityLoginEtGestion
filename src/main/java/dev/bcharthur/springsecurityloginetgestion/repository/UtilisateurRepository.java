package dev.bcharthur.springsecurityloginetgestion.repository;

import dev.bcharthur.springsecurityloginetgestion.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    /**
     * Méthode qui renvoie la plus grande Enchere
     */
    @Query("SELECT u FROM Utilisateur u WHERE u.pseudo = :#{#pseudo}")
    Utilisateur findByPseudo(String pseudo);
}
