package com.medical.gestion_des_consultation.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nomP", nullable = false)
    private String nomP;

    @Column(name = "prenomP", nullable = false)
    private String prenomP;

    @Column(name = "dateNaiss", nullable = false)
    private LocalDate dateNaiss;

    @ManyToOne
    @JoinColumn(name = "ville_id", nullable = false)
    private Ville ville;


    public Patient() {
    }

    public Patient(String nomP, String prenomP, LocalDate dateNaiss, Ville ville) {
        this.nomP = nomP;
        this.prenomP = prenomP;
        this.dateNaiss = dateNaiss;
        this.ville = ville;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public String getPrenomP() {
        return prenomP;
    }

    public void setPrenomP(String prenomP) {
        this.prenomP = prenomP;
    }

    public LocalDate getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(LocalDate dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }
}

