package dev.bcharthur.springsecurityloginetgestion.controller;

import dev.bcharthur.springsecurityloginetgestion.service.UtilisateurService;
import dev.bcharthur.springsecurityloginetgestion.model.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/utilisateurs")
public class UtilisateursController {
    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping
    public String getUtilisateurs(Model model) {
        model.addAttribute("listeUtilisateurs", utilisateurService.consulterUtilisateurs());
        return "utilisateurs/liste";
    }

    @GetMapping("/creer")
    public String getUtilisateurForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "utilisateurs/creation";
    }

    @PostMapping("/creer")
    public String postUtilisateurForm(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "utilisateurs/creation";
        }

        utilisateurService.creerUtilisateur(utilisateur);
        return "redirect:/utilisateurs";
    }

    @GetMapping("/{idUtilisateur}/supprimer")
    public String supprimerUtilisateur(@PathVariable int idUtilisateur, Model model) {
        Utilisateur utilisateur = utilisateurService.consulterUtilisateurParId(idUtilisateur);

        model.addAttribute("message", "Êtes vous sur de vouloir supprimer l'utilisateur : " + utilisateur.getPseudo());
        model.addAttribute("action", "/utilisateurs/" + idUtilisateur + "/supprimer");
        model.addAttribute("back", "/utilisateurs");

        return "interfaces/confirmation";
    }

    @PostMapping("/{idUtilisateur}/supprimer")
    public String supprimerUtilisateur(@PathVariable int idUtilisateur) {
        utilisateurService.supprimerUtilisateurParId(idUtilisateur);
        return "redirect:/utilisateurs";
    }

    @GetMapping("/{utilisateurId}/edit")
    public String getUtilisateurEditForm(@PathVariable("utilisateurId") int utilisateurId, Model model) {
        Utilisateur utilisateur = utilisateurService.consulterUtilisateurParId(utilisateurId);
        model.addAttribute("utilisateur", utilisateur);
        return "utilisateurs/modification";
    }

    @PostMapping("/edit")
    public String postUtilisateurEditForm(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "utilisateurs/modification";
        }

        utilisateurService.modifierUtilisateur(utilisateur);
        return "redirect:/utilisateurs";
    }

    @GetMapping("/{utilisateurId}/detail")
    public String getUtilisateurDetailForm(@PathVariable("utilisateurId") int utilisateurId, Model model) {
        Utilisateur utilisateur = utilisateurService.consulterUtilisateurParId(utilisateurId);
        model.addAttribute("utilisateur", utilisateur);
        return "utilisateurs/detail";
    }

    @PostMapping("/detail")
    public String postUtilisateurDetailForm(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "utilisateurs/detail";
        }

        utilisateurService.modifierUtilisateur(utilisateur);
        return "redirect:/utilisateurs";
    }
}
