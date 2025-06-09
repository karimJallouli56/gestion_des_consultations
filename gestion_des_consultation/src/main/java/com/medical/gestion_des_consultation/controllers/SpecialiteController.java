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

