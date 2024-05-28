package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositorios.AccountRepository;
import com.mindhub.homebanking.repositorios.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    // Endpoint para obtener todas las cuentas
    @GetMapping("/accounts")
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
    @GetMapping("/accounts/{id}")
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
    @PostMapping("/clients/current/accounts")
    public ResponseEntity<String> createAccount(Authentication authentication){
        Client client = clientRepository.findByEmail(authentication.getName());

        if(client.getAccounts().size()>=3){
            return new ResponseEntity<>("You reach the maximum limit of 3 accounts per client",HttpStatus.FORBIDDEN);
        }
        String numberAccount;
        do {
            int randomNumber = eightDigits();
            numberAccount = "VIN-" + String.format("%08d", randomNumber);
        } while (accountRepository.findByNumberAccount(numberAccount) != null);

        Account account = new Account(numberAccount, LocalDate.now(), 0.0);
        account.setClient(client);
        client.addAccount(account);

        clientRepository.save(client);
        accountRepository.save(account);


        return new ResponseEntity<>("Client account created", HttpStatus.CREATED);
    }

    public static int eightDigits() {
        Random random = new Random();
        return random.nextInt(100000000); // Genera un número entre 0 y 99999999
    }
}
