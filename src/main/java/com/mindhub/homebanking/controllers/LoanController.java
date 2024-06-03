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

    @GetMapping("/")
    public ResponseEntity<?> getLoans() {
        List<LoanDTO> loansDTO = loanService.getLoansDTO();
        if (loansDTO.isEmpty()) {
            return new ResponseEntity<>("Loans not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loansDTO, HttpStatus.OK);
    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyForLoan( @RequestBody LoanAplicationDTO loanAplicationDTO, Authentication authentication) {
        String userEmail = authentication.getName();
        return loanService.applyForLoan(loanAplicationDTO, userEmail);
    }
}