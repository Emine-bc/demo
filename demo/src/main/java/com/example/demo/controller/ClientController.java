package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController // controller generation2 : page web,text xml json(REST api)
public class ClientController {
    @Autowired
    ClientService agent;


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public  String test(){
        Client c = new Client();
        c.setNom("amine");
        c.setPrenom("bouchahda");
        agent.saveOrUpdate(c);
         return "client ajouté avec succes";
    }


    //design pattern mvc
    @RequestMapping(value = "/index",method = RequestMethod.GET)
     public ModelAndView index(){
        ModelAndView model = new ModelAndView();
        model.setViewName("index");//page retourner
        return (model);
    }

    @RequestMapping(value = "/clients/list",method = RequestMethod.GET)
    public ModelAndView list_clients(){
        List<Client> clients =  agent.consulte();
        ModelAndView model = new ModelAndView();
        model.addObject("clients",clients);
        model.setViewName("mes_clients");
        return (model);
    }





    @RequestMapping(value = "/clients/delete/{idc}",method = RequestMethod.GET)
    public ModelAndView delete_client(@PathVariable("idc") long idc){
        agent.delete(idc);
        return (new ModelAndView("redirect:/clients/list"));
    }




    @RequestMapping(value = "/clients/add",method = RequestMethod.GET)
    public ModelAndView form_client(){
        Client c = new Client();
        ModelAndView model = new ModelAndView();
        model.addObject("clientForm",c);
        model.setViewName("client");
        return (model);
    }

    @RequestMapping(value = "/clients/save",method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("clientForm") Client client, BindingResult result){

        if(result.hasErrors()){
            return (new ModelAndView("client"));
        }
        else {
            agent.saveOrUpdate(client);
            return(new ModelAndView("redirect:/clients/list"));
        }
    }
    @RequestMapping(value = "/clients/update/{idc}",method = RequestMethod.GET)
    public ModelAndView update_client(@PathVariable("idc") long idclient){
        Client c = agent.getById(idclient);
        ModelAndView model = new ModelAndView();
        model.addObject("clientForm", c);
        model.setViewName("editclient");
        return model;
    }
}
