package com.medical.gestion_des_consultation.controllers;

import com.medical.gestion_des_consultation.entities.Consultation;
import com.medical.gestion_des_consultation.entities.ConsultationId;
import com.medical.gestion_des_consultation.entities.Patient;
import com.medical.gestion_des_consultation.entities.Medecin;
import com.medical.gestion_des_consultation.services.ConsultationService;
import com.medical.gestion_des_consultation.services.MedecinService;
import com.medical.gestion_des_consultation.services.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/consultations")
//http://localhost:8088/consultations
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedecinService medecinService;

    @GetMapping
    public String listConsultations(Model model) {
        model.addAttribute("consultations", consultationService.findAll());
        return "list-consultations";
    }

    @GetMapping("/new")
    public String showCreateConsultationForm(Model model) {
        Consultation consultation = new Consultation();
        consultation.setId(new ConsultationId()); // Initialiser la clé composite
        model.addAttribute("consultation", consultation);
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("medecins", medecinService.findAll());
        return "add-consultation";
    }

    @PostMapping("/save")
    public String saveConsultation(@ModelAttribute("consultation") Consultation consultation) {
        Long patientId = consultation.getPatient().getId();
        Long medecinId = consultation.getMedecin().getId();
        LocalDate dateC = consultation.getDateC();

        Patient patient = patientService.findById(patientId);
        Medecin medecin = medecinService.findById(medecinId);

        consultation.setPatient(patient);
        consultation.setMedecin(medecin);
        consultation.setId(new ConsultationId(patientId, medecinId, dateC));

        consultationService.save(consultation);
        return "redirect:/consultations";
    }

    @PostMapping("/update")
    public String updateConsultation(
            @RequestParam("numP") Long numP,
            @RequestParam("numM") Long numM,
            @RequestParam("dateC") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateC,
            @ModelAttribute Consultation updatedConsultation) {

        Long newPatientId = updatedConsultation.getPatient().getId();
        Long newMedecinId = updatedConsultation.getMedecin().getId();
        LocalDate newDateC = updatedConsultation.getDateC();

        Patient patient = patientService.findById(newPatientId);
        Medecin medecin = medecinService.findById(newMedecinId);

        updatedConsultation.setPatient(patient);
        updatedConsultation.setMedecin(medecin);
        updatedConsultation.setId(new ConsultationId(newPatientId, newMedecinId, newDateC));

        consultationService.update(numP, numM, dateC, updatedConsultation);
        return "redirect:/consultations";
    }

    @GetMapping("/edit")
    public String showEditConsultationForm(
            @RequestParam("numP") Long numP,
            @RequestParam("numM") Long numM,
            @RequestParam("dateC") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateC,
            Model model) {

        Consultation consultation = consultationService.findById(numP, numM, dateC);
        model.addAttribute("consultation", consultation);
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("medecins", medecinService.findAll());
        return "edit-consultation";
    }


    @GetMapping("/delete")
    public String deleteConsultation(
            @RequestParam("numP") Long numP,
            @RequestParam("numM") Long numM,
            @RequestParam("dateC") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateC) {
        consultationService.deleteById(numP, numM, dateC);
        return "redirect:/consultations";
    }
    @GetMapping("/search")
    public String searchConsultations(@RequestParam String filter,
                                      @RequestParam String keyword,
                                      Model model) {
        List<Consultation> consultations;

        switch (filter) {
            case "patient":
                consultations = consultationService.findByPatientName(keyword);
                break;
            case "medecin":
                consultations = consultationService.findByMedecinName(keyword);
                break;
            case "date":
                consultations = consultationService.findByDate(LocalDate.parse(keyword));
                break;
            default:
                consultations = consultationService.findAll();
                break;
        }

        model.addAttribute("consultations", consultations);
        model.addAttribute("filter", filter);
        model.addAttribute("keyword", keyword);

        if (consultations.isEmpty()) {
            String readableFilter = switch (filter) {
                case "patient" -> "le patient";
                case "medecin" -> "le médecin";
                case "date" -> "la date";
                default -> "le filtre";
            };
            model.addAttribute("readableFilter", readableFilter);
            model.addAttribute("noResults", true);
        }

        return "list-consultations";
    }




}
