package com.medical.gestion_des_consultation.controllers;

import com.medical.gestion_des_consultation.entities.Consultation;
import com.medical.gestion_des_consultation.entities.ConsultationId;
import com.medical.gestion_des_consultation.entities.Medecin;
import com.medical.gestion_des_consultation.entities.Patient;
import com.medical.gestion_des_consultation.services.ConsultationService;
import com.medical.gestion_des_consultation.services.MedecinService;
import com.medical.gestion_des_consultation.services.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/consultations")
//http://localhost:8088/api/consultations
public class ConsultationRestController {

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedecinService medecinService;

    @GetMapping
    public List<Consultation> getAllConsultations() {
        return consultationService.findAll();
    }
    //http://localhost:8088/api/consultations/53/202/2024-06-02
    @GetMapping("/{numP}/{numM}/{dateC}")
    public Consultation getConsultationById(@PathVariable Long numP,
                                            @PathVariable Long numM,
                                            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateC) {
        return consultationService.findById(numP, numM, dateC);
    }

    @PostMapping
    public Consultation createConsultation(@RequestBody Consultation consultation) {
        Long patientId = consultation.getPatient().getId();
        Long medecinId = consultation.getMedecin().getId();
        LocalDate dateC = consultation.getDateC();

        Patient patient = patientService.findById(patientId);
        Medecin medecin = medecinService.findById(medecinId);

        consultation.setPatient(patient);
        consultation.setMedecin(medecin);
        consultation.setId(new ConsultationId(patientId, medecinId, dateC));

        return consultationService.save(consultation);
    }
    /**
     * Updates only the description of an existing consultation.
     * The patient ID, doctor ID, and consultation date cannot be modified.
     */
    @PutMapping("/{numP}/{numM}/{dateC}")
    public void updateConsultation(@PathVariable Long numP,
                                   @PathVariable Long numM,
                                   @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateC,
                                   @RequestBody Consultation updatedConsultation) {
        consultationService.update(numP, numM, dateC, updatedConsultation);
        //exemple in postman :
        //put
        //http://localhost:8088/api/consultations/53/202/2024-06-02
        /*{
           "description": "Suivi pour douleurs articulaires ."
        }*/
    }

    @DeleteMapping("/{numP}/{numM}/{dateC}")
    public void deleteConsultation(@PathVariable Long numP,
                                   @PathVariable Long numM,
                                   @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateC) {
        consultationService.deleteById(numP, numM, dateC);
    }

    @GetMapping("/search")
    public List<Consultation> searchConsultations(@RequestParam String filter,
                                                  @RequestParam String keyword) {
        switch (filter) {
            case "patient":
                return consultationService.findByPatientName(keyword);
            case "medecin":
                return consultationService.findByMedecinName(keyword);
            case "date":
                return consultationService.findByDate(LocalDate.parse(keyword));
            default:
                return consultationService.findAll();
        }
    }
}
