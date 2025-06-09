package com.medical.gestion_des_consultation.repositories;
import com.medical.gestion_des_consultation.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin,Long>{
    // Search by name (first, last or full)
    @Query("SELECT m FROM Medecin m WHERE " +
            "LOWER(m.nomM) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(m.prenomM) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(CONCAT(m.nomM, ' ', m.prenomM)) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(CONCAT(m.prenomM, ' ', m.nomM)) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Medecin> findByNomOrPrenom(@Param("name") String name);

    // Search by specialité (libelle)
    @Query("SELECT m FROM Medecin m WHERE LOWER(m.codeSp.libelle) LIKE LOWER(CONCAT('%', :specialite, '%'))")
    List<Medecin> findBySpecialite(@Param("specialite") String specialite);
}
