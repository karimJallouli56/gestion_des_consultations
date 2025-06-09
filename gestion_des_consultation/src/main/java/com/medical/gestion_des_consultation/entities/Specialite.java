package com.medical.gestion_des_consultation.entities;
import jakarta.persistence.*;
@Entity
@Table(name = "specialite")
public class Specialite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "libelle", nullable = false)
    private String libelle;

    public Specialite() {
    }


    public Specialite(String libelle) {
        this.libelle =libelle;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getLibelle() {
        return libelle;
    }


    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
