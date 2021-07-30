package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl  implements ClientService {
    @Autowired //automatic write and read (injection d un objet
    ClientRepository agent;

    @Override
    public void saveOrUpdate(Client c) {
        agent.save(c);
        //mail de confirmation
    }

    @Override
    public Client getById(long idc) {
        return (Client) agent.findById(idc).get();
    }

    @Override
    public void delete(long idc) {
        agent.deleteById(idc);
    }

    @Override
    public List<Client> consulte() {
        return (List<Client>) agent.findAll();
    }

}
