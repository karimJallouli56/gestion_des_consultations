package com.medical.gestion_des_consultation.controllers;

import com.medical.gestion_des_consultation.entities.Ville;
import com.medical.gestion_des_consultation.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/villes")
// Exemple : http://localhost:8088/api/villes
public class VilleRestController {

    @Autowired
    private VilleService villeService;

    //  GET all villes
    @GetMapping
    public List<Ville> getAllVilles() {
        return villeService.findAll();
    }

    //  GET ville by ID
    @GetMapping("/{id}")
    public Ville getVilleById(@PathVariable Long id) {
        return villeService.findById(id);
    }
    // Exemple : http://localhost:8088/api/villes/152
    /*{
        "id": 152,
        "nomV": "Tunis"
    }*/
    @PostMapping
    public Ville createVille(@RequestBody Ville ville) {
        return villeService.save(ville);
    }

    //  UPDATE ville
    @PutMapping("/{id}")
    public Ville updateVille(@PathVariable Long id, @RequestBody Ville ville) {
        return villeService.update(id, ville);
    }

    //  DELETE ville
    @DeleteMapping("/{id}")
    public void deleteVille(@PathVariable Long id) {
        villeService.deleteById(id);
    }
}
