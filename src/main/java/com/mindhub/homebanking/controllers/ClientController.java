package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClienteDTO;
import com.mindhub.homebanking.models.Cliente;
import com.mindhub.homebanking.repositorios.ClienteRepository;
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
    private ClienteRepository clienteRepository;

    // Endpoint para obtener todos los clientes
    @GetMapping("/")
    public ResponseEntity<?> getAllClients() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> ClienteDtolist = clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
        if (!clientes.isEmpty()) {
            return new ResponseEntity<>(ClienteDtolist, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se han encontrado clientes", HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para buscar un cliente por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            ClienteDTO clienteDTO = new ClienteDTO(cliente);
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}