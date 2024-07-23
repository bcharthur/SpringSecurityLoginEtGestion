package dev.bcharthur.springsecurityloginetgestion.controller;

import dev.bcharthur.springsecurityloginetgestion.service.UtilisateurService;
import dev.bcharthur.springsecurityloginetgestion.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public String getUtilisateurs(Model model) {
        model.addAttribute("listeUtilisateurs", utilisateurService.consulterUtilisateurs());
        return "auth/register";
    }

    @GetMapping("/creer")
    public String getUtilisateurForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "auth/register";
    }

    @PostMapping("/creer")
    public String postUtilisateurForm(@Valid Utilisateur utilisateur, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        try {
            utilisateurService.creerUtilisateur(utilisateur);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Un utilisateur avec ce pseudo, email ou téléphone existe déjà.");
            return "auth/register";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Une erreur inattendue s'est produite. Veuillez réessayer.");
            return "auth/register";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Utilisateur créé avec succès.");
        return "redirect:/";
    }

    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Une erreur s'est produite. Veuillez réessayer.");
        return "auth/register";
    }
}
