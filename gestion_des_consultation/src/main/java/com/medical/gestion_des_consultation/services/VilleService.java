package com.medical.gestion_des_consultation.services;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.medical.gestion_des_consultation.entities.Ville;
import com.medical.gestion_des_consultation.repositories.VilleRepository;


@Service
@Transactional
public class VilleService {
    @Autowired
    private VilleRepository villeRepository;


    public Ville save(Ville ville) {
        return villeRepository.save(ville);
    }


    public Ville findById(Long id) {
        return villeRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Unable to find room with id " + id));
    }


    public List<Ville> findAll() {
        return villeRepository.findAll();
    }


    public Ville update(Long id, Ville villeUpdate) {
        Ville foundVille = findById(id);
        foundVille.setNomV(villeUpdate.getNomV());
        return save(foundVille);
    }


    public void deleteById(Long id) {
        villeRepository.deleteById(id);
    }


    public void deleteAll() {
        villeRepository.deleteAll();
    }
}
