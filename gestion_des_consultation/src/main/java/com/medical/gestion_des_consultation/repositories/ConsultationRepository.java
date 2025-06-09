package com.medical.gestion_des_consultation.repositories;

import com.medical.gestion_des_consultation.entities.Consultation;
import com.medical.gestion_des_consultation.entities.ConsultationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, ConsultationId> {

    // Search by date
    @Query("SELECT c FROM Consultation c WHERE c.id.dateC = :date")
    List<Consultation> findByIdDateC(@Param("date") LocalDate date);
    // Search by full name of patient
    @Query("""
    SELECT c FROM Consultation c
    WHERE LOWER(CONCAT(c.patient.nomP, ' ', c.patient.prenomP)) LIKE LOWER(CONCAT('%', :fullName, '%'))
       OR LOWER(CONCAT(c.patient.prenomP, ' ', c.patient.nomP)) LIKE LOWER(CONCAT('%', :fullName, '%'))
""")
    List<Consultation> findByPatientNomContainingIgnoreCase(@Param("fullName") String fullName);

    // Search by full name of medecin
    @Query("""
    SELECT c FROM Consultation c
    WHERE LOWER(CONCAT(c.medecin.nomM, ' ', c.medecin.prenomM)) LIKE LOWER(CONCAT('%', :fullName, '%'))
       OR LOWER(CONCAT(c.medecin.prenomM, ' ', c.medecin.nomM)) LIKE LOWER(CONCAT('%', :fullName, '%'))
""")
    List<Consultation> findByMedecinNomContainingIgnoreCase(@Param("fullName") String fullName);





    //    // Search by patient name (first or last)
//    @Query("SELECT c FROM Consultation c " +
//            "WHERE LOWER(c.patient.nomP) LIKE LOWER(CONCAT('%', :name, '%')) " +
//            "   OR LOWER(c.patient.prenomP) LIKE LOWER(CONCAT('%', :name, '%'))")
//    List<Consultation> findByPatientNomContainingIgnoreCase(@Param("name") String name);
//
//    // Search by medecin name (first or last)
//    @Query("SELECT c FROM Consultation c " +
//            "WHERE LOWER(c.medecin.nomM) LIKE LOWER(CONCAT('%', :name, '%')) " +
//            "   OR LOWER(c.medecin.prenomM) LIKE LOWER(CONCAT('%', :name, '%'))")
//    List<Consultation> findByMedecinNomContainingIgnoreCase(@Param("name") String name);

}
