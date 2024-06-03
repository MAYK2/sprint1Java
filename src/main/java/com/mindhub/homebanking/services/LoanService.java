package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.LoanDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LoanService {
    List<LoanDTO> getLoansDTO();
}