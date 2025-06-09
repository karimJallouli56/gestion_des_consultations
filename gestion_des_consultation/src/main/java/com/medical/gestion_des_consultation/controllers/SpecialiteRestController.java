package com.medical.gestion_des_consultation.controllers;

import com.medical.gestion_des_consultation.entities.Specialite;
import com.medical.gestion_des_consultation.services.SpecialiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialites")
//http://localhost:8088/api/specialites
public class SpecialiteRestController {

    @Autowired
    private SpecialiteService specialiteService;

    @GetMapping
    public List<Specialite> listSpecialites() {
        return specialiteService.findAll();
    }

    @GetMapping("/{id}")
    public Specialite getSpecialite(@PathVariable Long id) {
        return specialiteService.findById(id);
    }

    @PostMapping
    public Specialite saveSpecialite(@RequestBody Specialite specialite) {
        return specialiteService.save(specialite);
    }

    @PutMapping("/{id}")
    public Specialite updateSpecialite(@PathVariable Long id, @RequestBody Specialite specialite) {
        return specialiteService.update(id, specialite);
    }

    @DeleteMapping("/{id}")
    public void deleteSpecialite(@PathVariable Long id) {
        specialiteService.deleteById(id);
    }
}
