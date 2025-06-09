package com.medical.gestion_des_consultation.controllers;

import com.medical.gestion_des_consultation.entities.Specialite;
import com.medical.gestion_des_consultation.services.SpecialiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/specialites")
public class SpecialiteController {

    @Autowired
    private SpecialiteService specialiteService;

    // Show list of specialites
    @GetMapping
    public String listSpecialites(Model model) {
        model.addAttribute("specialites", specialiteService.findAll());
        return "list-specialites";  // View: list-specialites.html
    }



    // Show form to create new specialite
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("specialite", new Specialite());
        return "add-specialite";  // View: add-add-specialite.html
    }

    // Save new specialite
    @PostMapping("/save")
    public String saveSpecialite(@ModelAttribute("specialite") Specialite specialite) {
        specialiteService.save(specialite);
        return "redirect:/specialites";
    }

    // Show form to edit specialite
    @GetMapping("/edit/{id}")
    public String showEditSpecialiteForm(@PathVariable Long id, Model model) {
        model.addAttribute("specialite", specialiteService.findById(id));
        return "edit-specialite";  // View: edit-add-specialite.html
    }

    // Update specialite
    @PostMapping("/update/{id}")
    public String updateSpecialite(@PathVariable Long id, @ModelAttribute Specialite specialite) {
        specialiteService.update(id, specialite);
        return "redirect:/specialites";
    }

    // Delete specialite
    @GetMapping("/delete/{id}")
    public String deleteSpecialite(@PathVariable Long id) {
        specialiteService.deleteById(id);
        return "redirect:/specialites";
    }
}
//package com.medical.gestion_des_consultation.controllers;
//
//import com.medical.gestion_des_consultation.entities.Specialite;
//import com.medical.gestion_des_consultation.services.SpecialiteService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//

//@RestController
//@RequestMapping("/api/specialites")


//    @Autowired
//    private SpecialiteService specialiteService;
//
//    @GetMapping
//    public List<Specialite> listSpecialites() {
//        return specialiteService.findAll();
//    }
//
//    @GetMapping("/api/{id}")
//    public Specialite getSpecialite(@PathVariable Long id) {
//        return specialiteService.findById(id);
//    }
//
//    @PostMapping
//    public Specialite saveSpecialite(@RequestBody Specialite specialite) {
//        return specialiteService.save(specialite);
//    }
//    @PutMapping("/api/{id}")
//    public Specialite updateSpecialite(@PathVariable Long id, @RequestBody Specialite specialite) {
//        return specialiteService.update(id, specialite);
//    }
//
//    @DeleteMapping("/api/{id}")
//    public void deleteSpecialite(@PathVariable Long id) {
//        specialiteService.deleteById(id);
//    }
//    [
//    { "libelle": "Cardiologie" },
//    { "libelle": "Neurologie" },
//    { "libelle": "Pédiatrie" },
//    { "libelle": "Dermatologie" },
//    { "libelle": "Oncologie" },
//    { "libelle": "Gynécologie" },
//    { "libelle": "Ophtalmologie" },
//    { "libelle": "Psychiatrie" },
//    { "libelle": "Urologie" },
//    { "libelle": "Radiologie" },
//    { "libelle": "Orthopédie" },
//    { "libelle": "Médecine interne" },
//    { "libelle": "Chirurgie générale" },
//    { "libelle": "Endocrinologie" },
//    { "libelle": "Néphrologie" },
//    { "libelle": "Généraliste" }
//]

//}
//@Controller
//@RequestMapping("/specialites")
//public class SpecialiteController {
//
//    @Autowired
//    private SpecialiteService specialiteService;
//
//    // Show list of specialites
//    @GetMapping
//    public String listSpecialites(Model model) {
//        model.addAttribute("specialites", specialiteService.findAll());
//        return "list-specialites";  // View: list-specialites.html
//    }
//
//
//
//    // Show form to create new specialite
//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//        model.addAttribute("specialite", new Specialite());
//        return "add-specialite";  // View: add-add-specialite.html
//    }
//
//    // Save new specialite
//    @PostMapping("/save")
//    public String saveSpecialite(@ModelAttribute("specialite") Specialite specialite) {
//        specialiteService.save(specialite);
//        return "redirect:/specialites";
//    }
//
//    // Show form to edit specialite
//    @GetMapping("/edit/{id}")
//    public String showEditSpecialiteForm(@PathVariable Long id, Model model) {
//        model.addAttribute("specialite", specialiteService.findById(id));
//        return "edit-patient";  // View: edit-add-specialite.html
//    }
//
//    // Update specialite
//    @PostMapping("/update/{id}")
//    public String updateSpecialite(@PathVariable Long id, @ModelAttribute Specialite specialite) {
//        specialiteService.update(id, specialite);
//        return "redirect:/specialites";
//    }
//
//    // Delete specialite
//    @GetMapping("/delete/{id}")
//    public String deleteSpecialite(@PathVariable Long id) {
//        specialiteService.deleteById(id);
//        return "redirect:/specialites";
//    }
//}
