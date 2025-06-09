package com.medical.gestion_des_consultation.services;

import com.medical.gestion_des_consultation.entities.Medecin;
import com.medical.gestion_des_consultation.entities.Specialite;

import com.medical.gestion_des_consultation.repositories.MedecinRepository;
import com.medical.gestion_des_consultation.repositories.SpecialiteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class MedecinService {
    @Autowired //injection de depandance
    private MedecinRepository medecinRepository;
    @Autowired //injection de depandance
    private SpecialiteRepository specialiteRepository;

    public Medecin save(Medecin medecin) {
        return medecinRepository.save(medecin);
    }


    public Medecin findById(Long id) {
        return medecinRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Unable to find medecin with id " + id));
    }


    public List<Medecin> findAll() {
        return medecinRepository.findAll();
    }


    public Medecin update(Long id,Medecin medecinUpdate) {
        Medecin foundMedecin = findById(id);
        foundMedecin.setNomM(medecinUpdate.getNomM());
        foundMedecin.setPrenomM(medecinUpdate.getPrenomM());
        foundMedecin.setAdresse(medecinUpdate.getAdresse());
        foundMedecin.setTel(medecinUpdate.getTel());
        Long specialiteId = medecinUpdate.getCodeSp().getId();
        Specialite specialite = specialiteRepository.findById(specialiteId)
                .orElseThrow(() -> new RuntimeException("Specialite not found"));
        foundMedecin.setCodeSp(specialite);
        return save(foundMedecin);
    }


    public void deleteById(Long id) {
        medecinRepository.deleteById(id);
    }


    public void deleteAll() {
        medecinRepository.deleteAll();
    }
    public List<Medecin> findByName(String name) {
        return medecinRepository.findByNomOrPrenom(name);
    }

    public List<Medecin> findBySpecialite(String libelle) {
        return medecinRepository.findBySpecialite(libelle);
    }


}
