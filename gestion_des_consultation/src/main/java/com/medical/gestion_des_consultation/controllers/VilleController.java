package com.medical.gestion_des_consultation.controllers;
import com.medical.gestion_des_consultation.entities.Ville;
import com.medical.gestion_des_consultation.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/villes")
//http://localhost:8088/villes
public class VilleController {
    @Autowired
    private VilleService villeService;


    @GetMapping
    public String listVilles(Model model) {
        model.addAttribute("villes", villeService.findAll());
        return "list-villes";
    }


    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("ville", new Ville());
        return "add-ville";
    }


    @PostMapping("/save")
    public String saveVille(@ModelAttribute("ville") Ville ville) {
        villeService.save(ville);
        return "redirect:/villes";
    }


    @GetMapping("/delete/{id}")
    public String deleteVille(@PathVariable Long id) {
        villeService.deleteById(id);
        return "redirect:/villes";    }

    @GetMapping("/edit/{id}")
    public String showEditVilleForm(@PathVariable Long id, Model model) {
        model.addAttribute("ville", villeService.findById(id));
        return "edit-ville";
    }


    @PostMapping("/update/{id}")
    public String updateVille(@PathVariable Long id, @ModelAttribute Ville ville) {
        villeService.update(id, ville);
        return "redirect:/villes";
    }
}
