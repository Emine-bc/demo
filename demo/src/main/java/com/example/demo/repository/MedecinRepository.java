package com.example.demo.repository;

import com.example.demo.model.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    public List<Medecin> findBySpecialite(String spec);

    public List<Medecin> findBySpecialiteAndNom(String spec, String nom);

    @Query(value = "select m from Medecin m where m.specialite=:spec and (m.nom LIKE :pseudo or m.prenom LIKE :pseudo)")
    public List<Medecin> advanced_search(@Param("spec") String s,@Param("pseudo") String P);


}
