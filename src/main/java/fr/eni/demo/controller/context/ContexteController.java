package fr.eni.demo.controller.context;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import fr.eni.demo.bo.context.Utilisateur;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/contexte/SessionEtRequest")
//Injection de la liste des attributs en session : utilisateurSession
@SessionAttributes({"utilisateurSession"})

public class ContexteController {

    @GetMapping("/invalidate")
    public String finSession(SessionStatus status) {
        // Suppression des attributs de @SessionAttributs status.setComplete();
        status.setComplete();
        return "redirect:/contexte/SessionEtRequest"; // Redirection
    }

        public String recupererAttributs (@ModelAttribute("utilisateurRequest")
                String utilisateurRequest, @ModelAttribute("utilisateurSession") String utilisateurSession){
            System.out.println("Contexte de la requête comporte : " + utilisateurRequest);
            System.out.println("Contexte de la session comporte : " + utilisateurSession);
            return "contexte/view-contexte";
        }

        @GetMapping
        public String addAttributRequest (
                @ModelAttribute("utilisateurSession") Utilisateur utilisateurSession, @RequestParam(name =
                "pseudo", required = false) String pseudo, Model model){
            System.out.println("Add Attribut Request");
            Utilisateur utilisateurRequest = new Utilisateur("Stéphane");
            model.addAttribute("utilisateurRequest", utilisateurRequest);
            System.out.println("Utilisateur en Session " + utilisateurSession);
            System.out.println("Paramètre – Pseudo = " + pseudo);

            if (pseudo != null) {
                utilisateurSession.setPseudo(pseudo);
            } else {
                utilisateurSession.setPseudo(null);
            }
            return "contexte/view-contexte";
        }

        // Cette méthode met par défaut un nouvel utilisateur en session
        @ModelAttribute("utilisateurSession")
        public Utilisateur addAttributSession () {
            System.out.println("Add Attribut Session");
            return new Utilisateur();

        }
    }

