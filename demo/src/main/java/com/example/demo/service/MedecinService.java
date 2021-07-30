package com.example.demo.service;


import com.example.demo.model.Medecin;

import java.util.List;

public interface MedecinService {
    public  void saveOrUpdate(Medecin medecin);
    public Medecin getById(long idc);
    public void delete(long idc);
    public List<Medecin> consulte();
    public List<Medecin> recherche_specialite(String spec);
    public List<Medecin> recherche_specialite_nom(String spec, String nom);
    public List<Medecin> recherche_avancee(String spec, String nom);
}
