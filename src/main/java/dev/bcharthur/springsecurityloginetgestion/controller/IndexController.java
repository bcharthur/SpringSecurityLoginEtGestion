package dev.bcharthur.springsecurityloginetgestion.controller;

import dev.bcharthur.springsecurityloginetgestion.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired // ici, Spring va automatiquement injecter une instance de classe (qui implémente l'interface FilmService) depuis le contexte Spring
    UtilisateurService utilisateurService;

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("listeUtilisateurs", utilisateurService.consulterUtilisateurs());

        return "index";
    }
}
