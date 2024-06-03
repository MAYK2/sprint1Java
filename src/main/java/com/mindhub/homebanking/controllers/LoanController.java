package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.LoanAplicationDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositorios.AccountRepository;
import com.mindhub.homebanking.repositorios.ClientRepository;
import com.mindhub.homebanking.repositorios.LoanRepository;
import com.mindhub.homebanking.repositorios.TransactionRepository;
import com.mindhub.homebanking.services.LoanService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    @Autowired
    private LoanService loanService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;


    @GetMapping("/")
    public ResponseEntity<?> getLoans() {
        List<LoanDTO> ListLoansDTO = loanService.getLoansDTO();

        if (ListLoansDTO.isEmpty()) {
            return new ResponseEntity<>("Loans not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ListLoansDTO, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/apply")
    public ResponseEntity<?> applyForLoan(@RequestBody LoanAplicationDTO loanAplicationDTO, Authentication authentication) {
        Client client = clientRepository.findByEmail(authentication.getName());
        String userName = authentication.getName();
        if (loanAplicationDTO.quantity() <= 0) {
            return new ResponseEntity<>("Please, check amount entry", HttpStatus.FORBIDDEN);
        }

        // Valida que el numero de cuotas sea positivo
        if (loanAplicationDTO.payments() <= 0) {
            return new ResponseEntity<>("Please, check the entries and try again", HttpStatus.FORBIDDEN);
        }

        // valida si NO existe un prestamo igual a la cuota.
        if (!loanRepository.existsById(loanAplicationDTO.id())) {
            return new ResponseEntity<>("Loan doesn't exist", HttpStatus.FORBIDDEN);
        }

        // Valida si el amount no excede el maxAmount del loan solicitado
        Loan loan = loanRepository.findById(loanAplicationDTO.id()).get();
        if (loanAplicationDTO.quantity() > loan.getMaxAmount()) {
            return new ResponseEntity<>("Loan amount exceeds maximum amount", HttpStatus.FORBIDDEN);
        }

        // Valida si las cuotas están dentro de las opciones del loan solicitado
        List<Integer> payments = loan.getPayments();
        if (!payments.contains(loanAplicationDTO.payments())) {
            return new ResponseEntity<>("The selected installments are not within the current options for this loan", HttpStatus.FORBIDDEN);
        }

        // Valida que la cuenta de destino exista
        Account destinationAccount = accountRepository.findByNumberAccount(loanAplicationDTO.destinationAccount());
        if (destinationAccount == null) {
            return new ResponseEntity<>("Destination account does not exist", HttpStatus.FORBIDDEN);
        }

        // Valida que la cuenta de destino pertenezca al cliente autenticado
        if (!destinationAccount.getClient().getEmail().equals(userName)) {
            return new ResponseEntity<>("Destination account does not belong to you.", HttpStatus.FORBIDDEN);
        }
        double interestRate = calculateInterestRate(loanAplicationDTO.payments());

        // Crear y configurar el nuevo préstamo con el monto y la tasa de interés
        double amountPlusInterest = loanAplicationDTO.quantity() + (loanAplicationDTO.quantity() * interestRate);
        ClientLoan newClientLoan = new ClientLoan(amountPlusInterest, loanAplicationDTO.payments());
        //newClientLoan.setClient(client);
        client.addClientLoan(newClientLoan);

        // Guardar entidades actualizadas
        clientRepository.save(client);

        // Crear y guardar la transacción
        String description = "New loan approved and credited";
        LocalDateTime date = LocalDateTime.now();
        Transaction transaction = new Transaction(loanAplicationDTO.quantity(), description, date, TypeTransaction.CREDIT);
        destinationAccount.addTransaction(transaction);
        transactionRepository.save(transaction);
        destinationAccount.setSaldo(destinationAccount.getSaldo() + loanAplicationDTO.quantity());
        accountRepository.save(destinationAccount);

        // return new ResponseEntity<>("Loan created and credited to destination account", HttpStatus.CREATED);
        return new ResponseEntity<>("Loan created and credited to destination account", HttpStatus.CREATED);
    }

    private double calculateInterestRate(int payments) {
        if (payments == 12) {
            return 0.20;
        } else if (payments > 12) {
            return 0.25;
        } else {
            return 0.15;
        }
    }
}
