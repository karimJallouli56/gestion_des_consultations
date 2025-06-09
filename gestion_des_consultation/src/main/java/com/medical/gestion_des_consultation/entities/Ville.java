package com.medical.gestion_des_consultation.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "villes")
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "nomV", nullable = false)
    private String nomV;

    public Ville() {
    }


    public Ville(String nomV) {
        this.nomV =nomV;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNomV() {
        return nomV;
    }


    public void setNomV(String nomV) {
        this.nomV = nomV;
    }
}
