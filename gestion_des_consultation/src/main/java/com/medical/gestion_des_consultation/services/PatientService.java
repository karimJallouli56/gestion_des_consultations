package com.medical.gestion_des_consultation.services;

import com.medical.gestion_des_consultation.entities.Patient;
import com.medical.gestion_des_consultation.entities.Ville;
import com.medical.gestion_des_consultation.repositories.PatientRepository;
import com.medical.gestion_des_consultation.repositories.VilleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;


@Service
@Transactional
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private VilleRepository villeRepository;

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }


    public Patient findById(Long id) {
        return patientRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Unable to find room with id " + id));
    }


    public List<Patient> findAll() {
        return patientRepository.findAll();
    }


    public Patient update(Long id, Patient patientUpdate) {
        Patient foundPatient = findById(id);
        foundPatient.setNomP(patientUpdate.getNomP());
        foundPatient.setPrenomP(patientUpdate.getPrenomP());
        foundPatient.setDateNaiss(patientUpdate.getDateNaiss());
        Long villeId = patientUpdate.getVille().getId();
        Ville ville = villeRepository.findById(villeId)
                .orElseThrow(() -> new RuntimeException("Ville not found"));
        foundPatient.setVille(ville);
        return save(foundPatient);
    }


    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }


    public void deleteAll() {
        patientRepository.deleteAll();
    }
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> findByName(String name) {
        return patientRepository.findByName(name);
    }

    public List<Patient> findByDateNaiss(LocalDate date) {
        return patientRepository.findByDateNaiss(date);
    }
    public List<Patient> findByNameAndDate(String name,LocalDate date) {
        return patientRepository.findByNameAndDate(name,date);
    }

}
