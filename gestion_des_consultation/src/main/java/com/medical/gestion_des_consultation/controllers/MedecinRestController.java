package com.medical.gestion_des_consultation.controllers;

import com.medical.gestion_des_consultation.entities.Medecin;
import com.medical.gestion_des_consultation.services.MedecinService;
import com.medical.gestion_des_consultation.services.SpecialiteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medecins")
// Exemple : http://localhost:8088/api/medecins
public class MedecinRestController {

    @Autowired
    private MedecinService medecinService;

    @Autowired
    private SpecialiteService specialiteService;

    // GET ALL
    @GetMapping
    public List<Medecin> getAllMedecins() {
        return medecinService.findAll();
    }



    // GET BY ID
    @GetMapping("/{id}")
    public Medecin getMedecinById(@PathVariable Long id) {
        return medecinService.findById(id);
    }
    //get output Json for id =202
    /*
        {
        "id": 202,
        "nomM": "Sassi",
        "prenomM": "nadhir",
        "adresse": "Rue de l'Indépendance, Tunis",
        "tel": 71234567,
        "codeSp": {
            "id": 202,
            "libelle": "Généraliste"
        }
    }*/

    // CREATE
    @PostMapping
    public Medecin createMedecin(@RequestBody Medecin medecin) {
        return medecinService.save(medecin);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Medecin updateMedecin(@PathVariable Long id, @RequestBody Medecin medecin) {
        return medecinService.update(id, medecin);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteMedecin(@PathVariable Long id) {
        medecinService.deleteById(id);
    }

    // SEARCH (par nom/prénom ou spécialité)
    @GetMapping("/search")
    public List<Medecin> searchMedecins(@RequestParam String filter,
                                        @RequestParam String keyword) {
        return switch (filter) {
            case "name" -> medecinService.findByName(keyword);
            case "specialite" -> medecinService.findBySpecialite(keyword);
            default -> medecinService.findAll();
        };
    }
}
