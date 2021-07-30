package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.model.Medecin;
import com.example.demo.model.RendezVs;

import com.example.demo.repository.RendezRepository;
import com.example.demo.service.ClientService;
import com.example.demo.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
@RestController
public class RdvController {


        @Autowired
        RendezRepository agent;

        @Autowired
        ClientService clientService;

        @Autowired
        MedecinService medecinService;

        @RequestMapping(value = "/rdvs/add",method = RequestMethod.GET)
        public ModelAndView add_rdv(){
            RendezVs rdv = new RendezVs();
            List<Client> Clients = clientService.consulte();
            List<Medecin> meds = medecinService.consulte();
            ModelAndView model = new ModelAndView();
            model.addObject("rdvForm",rdv);
            model.addObject("clients",Clients);
            model.addObject("medecins",meds);
            model.setViewName("rdv");
             return (model);
        }

        @RequestMapping(value = "/rdvs/save",method = RequestMethod.POST)
        public String save(@ModelAttribute("rdvForm") RendezVs rdv){
            agent.save(rdv);
            return ("rendez-vous ajouter");
        }



        /********************************************************************************/

        @RequestMapping(value = "/rdvs/list",method = RequestMethod.GET)
        public ModelAndView listRendevs(){
           List<RendezVs> RENDEZVS = (List<RendezVs>) agent.findAll();
            ModelAndView model = new ModelAndView();
            model.addObject("mesRendezvs",RENDEZVS);
            model.setViewName("mes_rendezvs");
            return (model);
        }

        /********************************************************************************/

}
