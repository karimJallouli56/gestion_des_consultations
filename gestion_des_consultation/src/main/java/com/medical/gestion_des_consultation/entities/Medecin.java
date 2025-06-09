package com.medical.gestion_des_consultation.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "medecin")
public class Medecin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nomM", nullable = false)
    private String nomM;

    @Column(name = "prenomM", nullable = false)
    private String prenomM;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "tel", nullable = false)
    private Integer tel;

    @ManyToOne
    @JoinColumn(name = "codeSp", nullable = false)
    private Specialite codeSp;

    public Medecin() {
    }

    public Medecin(String nomM, String prenomM, String adresse, Integer tel, Specialite codeSp) {
        this.nomM = nomM;
        this.prenomM = prenomM;
        this.adresse = adresse;
        this.tel = tel;
        this.codeSp = codeSp;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomM() {
        return nomM;
    }

    public void setNomM(String nomM) {
        this.nomM = nomM;
    }

    public String getPrenomM() {
        return prenomM;
    }

    public void setPrenomM(String prenomM) {
        this.prenomM = prenomM;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public Specialite getCodeSp() {
        return codeSp;
    }

    public void setCodeSp(Specialite codeSp) {
        this.codeSp = codeSp;
    }
}
