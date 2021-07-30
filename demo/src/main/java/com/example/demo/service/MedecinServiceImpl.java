package com.example.demo.service;

import com.example.demo.model.Medecin;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MedecinServiceImpl implements MedecinService{
    @Autowired //automatic write and read (injection d un objet
    MedecinRepository medecin;

    @Override
    public void saveOrUpdate(Medecin object_medecin) {
        medecin.save(object_medecin);
    }

    @Override
    public Medecin getById(long idc) {
        return (Medecin) medecin.findById(idc).get() ;
    }

    @Override
    public void delete(long idc) {
        medecin.deleteById(idc);
    }

    @Override
    public List<Medecin> consulte() {
        return (List<Medecin>) medecin.findAll();
    }

    @Override
    public List<Medecin> recherche_specialite(String spec) {
        return medecin.findBySpecialite(spec);
    }

    @Override
    public List<Medecin> recherche_specialite_nom(String spec, String nom) {
        return (medecin.findBySpecialiteAndNom(spec,nom));
    }

    @Override
    public List<Medecin> recherche_avancee(String spec, String nom) {
        return medecin.advanced_search(spec,"%"+nom+"%");
    }


}
