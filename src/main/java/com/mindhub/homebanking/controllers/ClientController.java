package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.models.Cliente;
import com.mindhub.homebanking.repositorios.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/")
    public List<Cliente> getAllClients() {
        return clienteRepository.findAll();
    }
}
