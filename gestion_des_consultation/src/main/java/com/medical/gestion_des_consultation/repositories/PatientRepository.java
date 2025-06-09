package com.medical.gestion_des_consultation.repositories;
import com.medical.gestion_des_consultation.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long>{
    @Query("SELECT p FROM Patient p WHERE " +
            "(LOWER(p.nomP) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
            "LOWER(p.prenomP) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "p.dateNaiss = :date")
    List<Patient> findByNameAndDate(@Param("name") String name, @Param("date") LocalDate date);
    // Nom or Prénom
    @Query("SELECT p FROM Patient p WHERE " +
            "LOWER(p.nomP) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
            "LOWER(p.prenomP) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Patient> findByName(@Param("name") String name);

    // Date only
    List<Patient> findByDateNaiss(LocalDate date);



}
