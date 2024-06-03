package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositorios.ClientRepository;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Endpoint para obtener todos los clientes
    @GetMapping("/")
    public ResponseEntity<?> getAllClients() {
        List<ClientDTO> clientsDtoList = clientService.getListClientsDTO();
        if (!clientsDtoList.isEmpty()) {
            return new ResponseEntity<>(clientsDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se han encontrado clientes", HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para buscar un cliente por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        if (client != null) {
            ClientDTO clientDTO = new ClientDTO(client);
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
