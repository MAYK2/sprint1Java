package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClientLoanDTO;
import com.mindhub.homebanking.dtos.LoanAplicationDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/my-loans")
    public ResponseEntity<?> getClientLoans(Authentication authentication) {
        String userEmail = authentication.getName();
        List<ClientLoanDTO> clientLoans = loanService.getClientLoans(userEmail);
        if (clientLoans.isEmpty()) {
            return new ResponseEntity<>("No loans found for the client", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientLoans, HttpStatus.OK);
    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyForLoan( @RequestBody LoanAplicationDTO loanAplicationDTO, Authentication authentication) {
        String userEmail = authentication.getName();
        return loanService.applyForLoan(loanAplicationDTO, userEmail);
    }
}