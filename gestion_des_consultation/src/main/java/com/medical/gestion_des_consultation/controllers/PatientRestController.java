package com.medical.gestion_des_consultation.controllers;

import com.medical.gestion_des_consultation.entities.Patient;
import com.medical.gestion_des_consultation.services.PatientService;
import com.medical.gestion_des_consultation.services.VilleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
// Exemple : http://localhost:8088/api/patients
public class PatientRestController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private VilleService villeService;

    // GET all patients
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.findAll();
    }

    // GET patient by ID
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.findById(id);
    }
    // Exemple : http://localhost:8088/api/patients/52
    /*
    {
        "id": 52,
        "nomP": "Ben Ali",
        "prenomP": "Sana",
        "dateNaiss": "1992-04-10",
        "ville": {
            "id": 152,
            "nomV": "Tunis"
        }
    }
    */

    // CREATE new patient
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.save(patient);
    }

    // UPDATE patient
    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        return patientService.update(id, patient);
    }

    // DELETE patient
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deleteById(id);
    }

    // SEARCH patients by name and/or birthdate
    @GetMapping("/search")
    public List<Patient> searchPatients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate) {

        boolean hasName = name != null && !name.isEmpty();
        boolean hasDate = birthDate != null;

        if (hasName && hasDate) {
            return patientService.findByNameAndDate(name, birthDate);
        } else if (hasName) {
            return patientService.findByName(name);
        } else if (hasDate) {
            return patientService.findByDateNaiss(birthDate);
        } else {
            return patientService.findAll();
        }
    }
}
