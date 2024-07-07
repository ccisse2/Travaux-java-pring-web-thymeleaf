package fr.eni.demo.controller;

import fr.eni.demo.bll.CoursService;
import fr.eni.demo.bll.FormateurService;
import fr.eni.demo.bo.Cours;
import fr.eni.demo.bo.Formateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/formateurs")
@SessionAttributes({"coursSession"})
public class FormateurController {

    private FormateurService formateurService;
    private CoursService coursService;

    public FormateurController(FormateurService formateurService, CoursService coursService) {
        this.formateurService = formateurService;
        this.coursService = coursService;
    }
    //Méthode pour charger la liste des cours en session
    @ModelAttribute("coursSession")
    public List<Cours> chargerCoursSession(){
        System.out.println("Chargement de la liste des cours en session");
        return coursService.getCours();
    }

    //Ajout d'un cours au formateur courant
    @PostMapping("/cours")
    public String ajouterCours(
            @RequestParam(required = true) String email,
            @RequestParam(name = "idCours", required = true) String id){
            long idCours = Long.parseLong(id);
            formateurService.updateCoursFormateur(email, idCours);
            return "redirect:/formateurs/detail?email=" + email;
    }

    @GetMapping
    public String afficherFormateurs(Model model){
        List<Formateur> lstFormateurs = formateurService.getFormateurs();
        model.addAttribute("formateurs", lstFormateurs);
        System.out.println(lstFormateurs);
        return "view-formateurs";
    }

    @GetMapping("/detail")
    public String detailFormateurParParametre(
            @RequestParam(name="email", required = false, defaultValue = "coach@campus-eni.fr")
            String emailFormateur, Model model
    ){
        System.out.println("Le parametre est : " + emailFormateur);
       Formateur formateur = formateurService.findByEmail(emailFormateur);
       model.addAttribute("formateur", formateur);
        return "view-formateur-detail";
    }
    @PostMapping("/detail")
    public String mettreAJourFormateur(
            @RequestParam(required = true) String email,
            @RequestParam(required = true) String nom,
            @RequestParam(required = true) String prenom){
        System.out.println("Email : " + email);
        System.out.println("nom : " + nom);
        System.out.println("prénom : " + prenom);
        Formateur formateur = formateurService.findByEmail(email);
        formateur.setNom(nom);
        formateur.setPrenom(prenom);
        formateurService.update(formateur);
        return "redirect:/formateurs";
    }
    @GetMapping({"/detail/variable/","/detail/variable/{email}"})
    public String detailFormateurParVariable(
            @PathVariable(name="email", required = false) String emailFormateur){
        if ((emailFormateur == null)){
            emailFormateur = "coach@campus-eni.fr";
        }
        System.out.println("Le parametre est : " + emailFormateur);
        return "view-formateurs";
    }
}
