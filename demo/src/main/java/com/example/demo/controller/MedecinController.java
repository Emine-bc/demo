package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.model.Medecin;
import com.example.demo.model.Recherche;
import com.example.demo.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MedecinController {
    @Autowired
    MedecinService MEDECIN;

    @RequestMapping(value = "/ajout/medecin",method = RequestMethod.GET)
    public String AjoutMedecin(){
        Medecin medecin =  new Medecin();
        medecin.setNom("ali");
        medecin.setPrenom("trad");
        medecin.setSpecialite("spec 2");
        MEDECIN.saveOrUpdate(medecin);
        return ("medecin ajouter avec succes");
    }

    /***************************************************************/
     @RequestMapping(value = "/medecins/add",method = RequestMethod.GET)
     public ModelAndView form_medecin(){
     Medecin c = new Medecin();
     ModelAndView model = new ModelAndView();
     model.addObject("medecintForm",c);
     model.setViewName("medecin");
     return (model);
     }

     @RequestMapping(value = "/medecins/save",method = RequestMethod.POST)
     public ModelAndView save(@Valid @ModelAttribute("medecinForm") Medecin medecin, BindingResult result){

     if(result.hasErrors()){
           return (new ModelAndView("medecin"));
         }
     else {
         MEDECIN.saveOrUpdate(medecin);
             return(new ModelAndView("redirect:/medecins/list"));
         }
     }

    @RequestMapping(value = "/medecins/update/{idc}",method = RequestMethod.GET)
    public ModelAndView update_client(@PathVariable("idc") long id){
        Medecin medecin  = MEDECIN.getById(id);
        ModelAndView model = new ModelAndView();
        model.addObject("medecinForm", medecin);
        model.setViewName("editmedecin");
        return model;
    }




    /*******************************************************************/







    @RequestMapping(value = "/medecins/list",method = RequestMethod.GET)
    public ModelAndView ListeMedecin(){
        List<Medecin> Medecins =  MEDECIN.consulte();
        ModelAndView model = new ModelAndView();
        model.addObject("Medecins",Medecins);
        model.setViewName("mes_medecins");
        return (model);
    }

    @RequestMapping(value = "/medecins/delete/{idc}",method = RequestMethod.GET)
    public ModelAndView delete_client(@PathVariable("idc") long idc){
        MEDECIN.delete(idc);
        return (new ModelAndView("redirect:/medecins/list"));
    }

    @RequestMapping(value = "medecins/spec",method = RequestMethod.GET)
    public ModelAndView recherche(){
        Recherche result =  new Recherche(); //LE MODELE
        ModelAndView model = new ModelAndView();
        model.addObject("resForm",result);
        model.setViewName("recherche_spec");
        return (model);
    }

    @RequestMapping(value = "medecins/recherche",method = RequestMethod.POST)
    public ModelAndView recherche_spec(@ModelAttribute("resForm") Recherche res){
       List<Medecin> meds = MEDECIN.recherche_specialite(res.getSpecialite());
       res.setMedecins(meds);
       ModelAndView model = new ModelAndView();
       model.addObject("resForm",res);
       model.setViewName("recherche_spec");
       return (model);
    }


    @RequestMapping(value = "/medecins/specnom",method = RequestMethod.GET)
    public ModelAndView recherche_specnom(){
        Recherche result =  new Recherche(); //LE MODELE
        ModelAndView model = new ModelAndView();
        model.addObject("resForm",result);
        model.setViewName("recherche_specnom");
        return (model);
    }

    @RequestMapping(value = "/medecins/recherchespecnom",method = RequestMethod.POST)
    public ModelAndView recherche_specnom(@ModelAttribute("resForm") Recherche res){
        List<Medecin> meds = MEDECIN.recherche_specialite_nom(res.getSpecialite(), res.getNom());
        res.setMedecins(meds);
        ModelAndView model = new ModelAndView();
        model.addObject("resForm",res);
        model.setViewName("recherche_specnom");
        return (model);
    }


    @RequestMapping(value = "/medecins/advanced",method = RequestMethod.GET)
    public ModelAndView recherche_advanced(){
        Recherche result =  new Recherche(); //LE MODELE
        ModelAndView model = new ModelAndView();
        model.addObject("resForm",result);
        model.setViewName("recherche_advanced");
        return (model);
    }

    @RequestMapping(value = "/medecins/rechercheadvanced",method = RequestMethod.POST)
    public ModelAndView recherche_advanced(@ModelAttribute("resForm") Recherche res){
        List<Medecin> meds = MEDECIN.recherche_avancee(res.getSpecialite(), res.getNom());
        res.setMedecins(meds);
        ModelAndView model = new ModelAndView();
        model.addObject("resForm",res);
        model.setViewName("recherche_advanced");
        return (model);
    }
}
