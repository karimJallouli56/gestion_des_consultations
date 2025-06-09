package com.medical.gestion_des_consultation.services;

import com.medical.gestion_des_consultation.entities.Specialite;
import com.medical.gestion_des_consultation.repositories.SpecialiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SpecialiteService {

    @Autowired
    private SpecialiteRepository specialiteRepository;

    public Specialite save(Specialite specialite) {
        return specialiteRepository.save(specialite);
    }

    public Specialite findById(Long id) {
        return specialiteRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Unable to find specialite with id " + id));
    }

    public List<Specialite> findAll() {
        return specialiteRepository.findAll();
    }

    public Specialite update(Long id, Specialite specialiteUpdate) {
        Specialite found = findById(id);
        found.setLibelle(specialiteUpdate.getLibelle());
        return save(found);
    }

    public void deleteById(Long id) {
        specialiteRepository.deleteById(id);
    }

    public void deleteAll() {
        specialiteRepository.deleteAll();
    }
}
