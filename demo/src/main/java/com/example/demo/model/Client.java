package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="clients")
public class Client {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="nom_client")
    private String nom;

    @Column(name= "prenom_client")
    private  String prenom;

    public Client() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @OneToMany(mappedBy = "client",cascade =CascadeType.ALL,fetch = FetchType.LAZY)
    private List<RendezVs> rdvs;

    public List<RendezVs> getRdvs(){
        return (rdvs);
    }
    public void  setRdvs(List<RendezVs> rdvs){
        this.rdvs =rdvs;
    }
}
