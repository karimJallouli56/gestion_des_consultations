package com.medical.gestion_des_consultation.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "consultation")
public class Consultation {

    @EmbeddedId
    private ConsultationId id;

    @ManyToOne
    @MapsId("numP")
    @JoinColumn(name = "numP")
    private Patient patient;

    @ManyToOne
    @MapsId("numM")
    @JoinColumn(name = "numM")
    private Medecin medecin;

    @Column(nullable = false)
    private String description;

    public Consultation() {
        this.id = new ConsultationId();
    }

    public Consultation(Patient patient, Medecin medecin, LocalDate dateC, String description) {
        this.patient = patient;
        this.medecin = medecin;
        this.description = description;
        this.id = new ConsultationId(patient.getId(), medecin.getId(), dateC);
    }

    // Getters & Setters
    public ConsultationId getId() { return id; }
    public void setId(ConsultationId id) { this.id = id; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Medecin getMedecin() { return medecin; }
    public void setMedecin(Medecin medecin) { this.medecin = medecin; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDateC() { return id.getDateC(); }
    public void setDateC(LocalDate dateC) { this.id.setDateC(dateC); }
}
