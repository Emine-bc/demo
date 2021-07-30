package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.model.Medecin;
import com.example.demo.model.RendezVs;

import com.example.demo.repository.RendezRepository;
import com.example.demo.service.ClientService;
import com.example.demo.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
        public ModelAndView save(@Valid@ModelAttribute("rdvForm") RendezVs rdv, BindingResult result){
            if(result.hasErrors()){
                return (new ModelAndView("rdv"));
            }
            else {
                agent.save(rdv);
                return(new ModelAndView("redirect:/rdvs/list"));
            }
        }



    @RequestMapping(value = "/rdvs/update/{idc}",method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("idc")long idrv){
        RendezVs rendezvs =agent.findById(idrv).get();
        List<Client> clients = clientService.consulte();//get list clients
        List<Medecin> meds = medecinService.consulte();//get list medecins
        ModelAndView model = new ModelAndView();
        model.addObject("rdvForm", rendezvs);
        model.addObject("clients", clients);//select clients
        model.addObject("medecins", meds);//select medecins
        model.setViewName("editrendezvs");
        return model;
    }




    @RequestMapping(value = "/rdvs/delete/{idc}",method = RequestMethod.GET)
    public ModelAndView delete_rendezvs(@PathVariable("idc") RendezVs idc){
        agent.delete(idc);
        return (new ModelAndView("redirect:/rdvs/list"));
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
