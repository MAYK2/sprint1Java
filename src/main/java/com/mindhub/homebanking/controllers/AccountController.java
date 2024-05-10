package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.repositorios.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    // Endpoint para obtener todas las cuentas
    @GetMapping("/cuentas")
    public ResponseEntity<?> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOList = accounts.stream().map(AccountDTO::new).collect(Collectors.toList());
        if (!accounts.isEmpty()) {
            return new ResponseEntity<>(accountDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se han encontrado cuentas", HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para buscar una cuenta por su ID
    @GetMapping("/cuentas/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Long id) {
        Optional<Account> cuentaOptional = accountRepository.findById(id);
        if (cuentaOptional.isPresent()) {
            Account account = cuentaOptional.get();
            AccountDTO accountDTO = new AccountDTO(account);
            return new ResponseEntity<>(accountDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cuenta no encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
