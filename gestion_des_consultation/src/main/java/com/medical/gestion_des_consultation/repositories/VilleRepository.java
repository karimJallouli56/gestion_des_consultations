package com.medical.gestion_des_consultation.repositories;
import com.medical.gestion_des_consultation.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VilleRepository extends JpaRepository<Ville,Long>{
}
