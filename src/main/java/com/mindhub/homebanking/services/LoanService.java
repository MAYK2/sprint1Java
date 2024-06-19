package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.ClientLoanDTO;
import com.mindhub.homebanking.dtos.LoanAplicationDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LoanService {
    List<LoanDTO> getLoansDTO();
    ResponseEntity<?> applyForLoan(LoanAplicationDTO loanAplicationDTO, String userEmail);
    List<ClientLoanDTO> getClientLoans(String userEmail);
}