package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositorios.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*")

public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    // Endpoint para obtener todos los clientes
    @GetMapping("/")
    public ResponseEntity<?> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        List<ClientDTO> clientDtolist = clients.stream().map(ClientDTO::new).collect(Collectors.toList());
        if (!clients.isEmpty()) {
            return new ResponseEntity<>(clientDtolist, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se han encontrado clientes", HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para buscar un cliente por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        Optional<Client> clienteOptional = clientRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Client client = clienteOptional.get();
            ClientDTO clientDTO = new ClientDTO(client);
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}