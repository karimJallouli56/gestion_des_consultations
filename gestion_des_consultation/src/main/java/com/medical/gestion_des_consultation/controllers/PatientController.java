package com.medical.gestion_des_consultation.controllers;
import com.medical.gestion_des_consultation.entities.Patient;
import com.medical.gestion_des_consultation.services.PatientService;
import com.medical.gestion_des_consultation.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.util.List;
@Controller
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private VilleService villeService;


    @GetMapping
    public String listpatients(Model model) {
        model.addAttribute("patients", patientService.findAll());
        return "list-patients";
    }


    @GetMapping("/new")
    public String showCreatePatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("villes", villeService.findAll());
        return "add-patient";
    }


    @PostMapping("/save")
    public String savePatient(@ModelAttribute("patient") Patient patient) {
        patientService.save(patient);
        return "redirect:/patients";
    }


//
//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable Long id, Model model) {
//        model.addAttribute("room", roomService.findById(id));
//        return "edit-room";
//    }


//    @PostMapping("/update/{id}")
//    public String updateRoom(@PathVariable Long id, @ModelAttribute Room room) {
//        roomService.update(id, room);
//        return "redirect:/rooms";
//    }


    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deleteById(id);
        return "redirect:/patients";    }
//
//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable Long id, Model model) {
//        Room room = roomService.findById(id);
//        model.addAttribute("room", room);
//        return "add-room"; // reuse the form view for edit
//    }




    @GetMapping("/edit/{id}")
    public String showEditPatientForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.findById(id));
        model.addAttribute("villes", villeService.findAll());
        return "edit-patient";
    }


    @PostMapping("/update/{id}")
    public String updatPatient(@PathVariable Long id, @ModelAttribute Patient patient) {
        patientService.update(id, patient);
        return "redirect:/patients";
    }
    @GetMapping("/search")
    public String searchPatients(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate,
                                 Model model) {
        List<Patient> patients;

        boolean hasName = name != null && !name.isEmpty();
        boolean hasDate = birthDate != null;

        if (hasName && hasDate) {
            patients = patientService.findByNameAndDate(name,birthDate);
        } else if (hasName) {
            patients = patientService.findByName(name);
        } else if (hasDate) {
            patients = patientService.findByDateNaiss(birthDate);
        } else {
            patients = patientService.findAll();
        }

        model.addAttribute("patients", patients);
        model.addAttribute("name", name);
        model.addAttribute("birthDate", birthDate);

        if (patients.isEmpty()) {
            model.addAttribute("noResults", true);

            if (hasName && hasDate) {
                model.addAttribute("readableFilter", "nom et date de naissance");
                model.addAttribute("keyword", name + " / " + birthDate.toString());
            } else if (hasName) {
                model.addAttribute("readableFilter", "nom");
                model.addAttribute("keyword", name);
            } else if (hasDate) {
                model.addAttribute("readableFilter", "date de naissance");
                model.addAttribute("keyword", birthDate.toString());
            }
        }

        return "list-patients"; // Your view name
    }
}
