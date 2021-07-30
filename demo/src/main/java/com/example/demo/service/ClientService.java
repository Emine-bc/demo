package com.example.demo.service;

import com.example.demo.model.Client;

import java.util.List;

public interface ClientService {
    public  void saveOrUpdate(Client c);
    public Client getById(long idc);
    public void delete(long idc);
    public List<Client> consulte();
}
