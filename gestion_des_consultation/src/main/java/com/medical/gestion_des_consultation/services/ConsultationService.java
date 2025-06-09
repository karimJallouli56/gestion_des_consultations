package com.medical.gestion_des_consultation.services;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import com.medical.gestion_des_consultation.entities.*;
import com.medical.gestion_des_consultation.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    public List<Consultation> findAll() {
        return consultationRepository.findAll();
    }

    public Consultation findById(Long numP, Long numM, LocalDate dateC) {
        return consultationRepository.findById(new ConsultationId(numP, numM, dateC))
                .orElseThrow(() -> new RuntimeException("Consultation non trouvée"));
    }

    public Consultation save(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    public void deleteById(Long numP, Long numM, LocalDate dateC) {
        consultationRepository.deleteById(new ConsultationId(numP, numM, dateC));
    }
    public void update(Long numP, Long numM, LocalDate dateC, Consultation updatedConsultation) {
        ConsultationId id = new ConsultationId(numP, numM, dateC);
        Optional<Consultation> optionalConsultation = consultationRepository.findById(id);

        if (optionalConsultation.isPresent()) {
            Consultation existing = optionalConsultation.get();
            existing.setDescription(updatedConsultation.getDescription());
            existing.setMedecin(updatedConsultation.getMedecin());
            existing.setPatient(updatedConsultation.getPatient());
            consultationRepository.save(existing);
        } else {
            throw new EntityNotFoundException("Consultation not found with ID: " + id);
        }
    }
    public List<Consultation> findByPatientName(String name) {
        return consultationRepository.findByPatientNomContainingIgnoreCase(name);
    }

    public List<Consultation> findByMedecinName(String name) {
        return consultationRepository.findByMedecinNomContainingIgnoreCase(name);
    }

    public List<Consultation> findByDate(LocalDate date) {
        return consultationRepository.findByIdDateC(date);
    }


}
