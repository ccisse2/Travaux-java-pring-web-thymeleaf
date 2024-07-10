package fr.eni.demo.controller;

import fr.eni.demo.bll.CoursService;
import fr.eni.demo.bll.FormateurService;
import fr.eni.demo.bo.Cours;
import fr.eni.demo.bo.Formateur;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/formateurs")
@SessionAttributes({"coursSession"})
public class FormateurController {
    private final FormateurService formateurService;
    private final CoursService coursService;

    public FormateurController(FormateurService formateurService, CoursService coursService) {
        this.formateurService = formateurService;
        this.coursService = coursService;
    }

    @GetMapping("/creer")
    public String creerFormateur(Model model) {
        Formateur formateur = new Formateur();
        model.addAttribute("formateur", formateur);
        return "view-formateur-creer";
    }

    @PostMapping("/creer")
    public String creerFormateur(@Valid @ModelAttribute("formateur") Formateur formateur,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "view-formateur-creer";
        } else {
            System.out.println(formateur.getListeCours());
            formateurService.add(formateur);
            return "redirect:/formateurs";
        }
    }

    @ModelAttribute("coursSession")
    public List<Cours> chargerCoursSession() {
        System.out.println("Chargement de la liste des cours en session");
        return coursService.getCours();
    }

    @PostMapping("/cours")
    public String ajouterCours(
            @RequestParam(required = true) String email,
            @RequestParam(name = "idCours", required = true) String id) {
        long idCours = Long.parseLong(id);
        formateurService.updateCoursFormateur(email, idCours);
        return "redirect:/formateurs/detail?email=" + email;
    }

    @GetMapping
    public String afficherFormateurs(Model model) {
        List<Formateur> lstFormateurs = formateurService.getFormateurs();
        model.addAttribute("formateurs", lstFormateurs);
        return "view-formateurs";
    }

    @GetMapping("/detail")
    public String detailFormateurParParametre(
            @RequestParam(name = "email", required = false, defaultValue = "coach@campus-eni.fr") String emailFormateur,
            Model model) {
        Formateur formateur = formateurService.findByEmail(emailFormateur);
        model.addAttribute("formateur", formateur);
        return "view-formateur-detail";
    }

    @PostMapping("/detail")
    public String mettreAJourFormateur(@Valid @ModelAttribute("formateur") Formateur formateur,
                                       BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "view-formateur-detail";
        } else {
            formateurService.update(formateur);
            return "redirect:/formateurs";
        }
    }

    @GetMapping({"/detail/variable/", "/detail/variable/{email}"})
    public String detailFormateurParVariable(
            @PathVariable(name = "email", required = false) String emailFormateur) {
        if ((emailFormateur == null)) {
            emailFormateur = "coach@campus-eni.fr";
        }
        System.out.println("Le parametre est : " + emailFormateur);
        return "view-formateurs";
    }
}

