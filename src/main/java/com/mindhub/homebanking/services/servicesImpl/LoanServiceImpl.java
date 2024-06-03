package com.mindhub.homebanking.services.servicesImpl;

import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.Loan;
import com.mindhub.homebanking.repositorios.LoanRepository;
import com.mindhub.homebanking.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<LoanDTO> getLoansDTO() {
        List<Loan> loans = loanRepository.findAll();
        return loans.stream().map(LoanDTO::fromLoan).collect(Collectors.toList());
    }
}
