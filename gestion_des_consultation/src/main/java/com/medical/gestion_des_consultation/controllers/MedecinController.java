package com.medical.gestion_des_consultation.controllers;
import com.medical.gestion_des_consultation.entities.Medecin;
import com.medical.gestion_des_consultation.services.MedecinService;
import com.medical.gestion_des_consultation.services.SpecialiteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/medecins")
//http://localhost:8088/medecins
public class MedecinController {
    @Autowired
    private MedecinService medecinService;
    @Autowired
    private  SpecialiteService specialiteService;


    @GetMapping
    public String listmedecins(Model model) {
        model.addAttribute("medecins", medecinService.findAll());
        return "list-medecins";
    }


    @GetMapping("/new")
    public String showCreateMedecinForm(Model model) {
        model.addAttribute("medecin", new Medecin());
        model.addAttribute("specialites", specialiteService.findAll()); // use "specialites" plural
        return "add-medecin";
    }


    @PostMapping("/save")
    public String saveMedecin(@ModelAttribute("medecin") Medecin medecin) {
        medecinService.save(medecin);
        return "redirect:/medecins";
    }




    @GetMapping("/delete/{id}")
    public String deleteMedecin(@PathVariable Long id) {
        medecinService.deleteById(id);
        return "redirect:/medecins";    }

    @GetMapping("/edit/{id}")
    public String showEditMedecinForm(@PathVariable Long id, Model model) {
        model.addAttribute("medecin", medecinService.findById(id));
        model.addAttribute("specialites", specialiteService.findAll());
        return "edit-medecin";
    }


    @PostMapping("/update/{id}")
    public String updatMedecin(@PathVariable Long id, @ModelAttribute Medecin medecin) {
        medecinService.update(id, medecin);
        return "redirect:/medecins";
    }
    @GetMapping("/search")
    public String searchMedecins(@RequestParam String filter,
                                 @RequestParam String keyword,
                                 Model model) {
        List<Medecin> medecins;

        switch (filter) {
            case "name":
                medecins = medecinService.findByName(keyword);
                break;
            case "specialite":
                medecins = medecinService.findBySpecialite(keyword);
                break;
            default:
                medecins = medecinService.findAll();
        }

        model.addAttribute("medecins", medecins);
        model.addAttribute("filter", filter);
        model.addAttribute("keyword", keyword);

        if (medecins.isEmpty()) {
            String readableFilter = switch (filter) {
                case "name" -> "le nom ou prénom";
                case "specialite" -> "la spécialité";
                default -> "le filtre";
            };
            model.addAttribute("readableFilter", readableFilter);
            model.addAttribute("noResults", true);
        }

        return "list-medecins";
    }
}
