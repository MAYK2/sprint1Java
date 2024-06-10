package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.dtos.TransferRequestDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.services.ClientService;
import com.mindhub.homebanking.services.TransactionService;
import com.mindhub.homebanking.services.servicesImpl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ClientService clientService;

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequestDTO transferRequest, Authentication authentication) {
        try {
            // Obtiene el correo electrónico del cliente autenticado
            String userEmail = authentication.getName();

            // Busca el cliente en la base de datos utilizando el correo electrónico
            Client client = clientService.getClientByEmail(userEmail);

            // Llama al método de transferencia en el servicio de transacciones
            transactionService.transfer(transferRequest, client);

            return ResponseEntity.ok("Transferencia exitosa");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transferencia fallida: " + e.getMessage());
        }
    }

    @GetMapping("/history")
    public ResponseEntity<?> getTransactions(Authentication authentication) {
        try {
            // Obtiene el correo electrónico del cliente autenticado
            String userEmail = authentication.getName();

            // Busca el cliente en la base de datos utilizando el correo electrónico
            Client client = clientService.getClientByEmail(userEmail);

            // Obtiene el historial de transacciones para el cliente
            List<TransactionDTO> transactionDTOs = transactionService.getTransactionsForClient(client).stream()
                    .map(TransactionDTO::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(transactionDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al obtener el historial de transacciones: " + e.getMessage());
        }
    }
}