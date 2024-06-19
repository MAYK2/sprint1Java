package com.mindhub.homebanking.services.servicesImpl;

import com.mindhub.homebanking.dtos.ClientLoanDTO;
import com.mindhub.homebanking.dtos.LoanAplicationDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import com.mindhub.homebanking.services.LoanService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ClientRepository clientRepository; //*
    @Autowired
    private AccountRepository accountRepository; //*
    @Autowired
    private TransactionsRepository transactionRepository;//*
    @Autowired
    private ClientLoanRepository clientLoanRepository; //*


    @Override
    public List<LoanDTO> getLoansDTO() {
        List<Loan> loans = loanRepository.findAll();
        return loans.stream().map(LoanDTO::fromLoan).collect(Collectors.toList());
    }
    @Override
    @Transactional
    public ResponseEntity<?> applyForLoan(LoanAplicationDTO loanAplicationDTO, String userEmail) {
        Client client = clientRepository.findByEmail(userEmail);

        if (loanAplicationDTO.quantity() <= 0) {
            return new ResponseEntity<>("Please, check amount entry", HttpStatus.FORBIDDEN);
        }

        if (loanAplicationDTO.payments() <= 0) {
            return new ResponseEntity<>("Please, check the entries and try again", HttpStatus.FORBIDDEN);
        }

        if (!loanRepository.existsById(loanAplicationDTO.id())) {
            return new ResponseEntity<>("Loan doesn't exist", HttpStatus.FORBIDDEN);
        }

        Loan loan = loanRepository.findById(loanAplicationDTO.id()).get();
        if (loanAplicationDTO.quantity() > loan.getMaxAmount()) {
            return new ResponseEntity<>("Loan amount exceeds maximum amount", HttpStatus.FORBIDDEN);
        }


        List<Integer> payments = loan.getPayments();
        if (!payments.contains(loanAplicationDTO.payments())) {
            return new ResponseEntity<>("The selected installments are not within the current options for this loan", HttpStatus.FORBIDDEN);
        }

        Account destinationAccount = accountRepository.findByNumberAccount(loanAplicationDTO.destinationAccount());
        if (destinationAccount == null) {
            return new ResponseEntity<>("Destination account does not exist", HttpStatus.FORBIDDEN);
        }

        if (!destinationAccount.getClient().getEmail().equals(userEmail)) {
            return new ResponseEntity<>("Destination account does not belong to you.", HttpStatus.FORBIDDEN);
        }

        double interestRate = calculateInterestRate(loanAplicationDTO.payments());
        double amountPlusInterest = loanAplicationDTO.quantity() + (loanAplicationDTO.quantity() * interestRate);
        ClientLoan newClientLoan = new ClientLoan(amountPlusInterest, loanAplicationDTO.payments());
        client.addClientLoan(newClientLoan);

        clientRepository.save(client);

        String description = "New loan approved and credited";
        LocalDateTime date = LocalDateTime.now();
        Transaction transaction = new Transaction(loanAplicationDTO.quantity(), description, date, TypeTransaction.CREDIT);
        destinationAccount.addTransaction(transaction);
        transactionRepository.save(transaction);
        destinationAccount.setBalance(destinationAccount.getBalance() + loanAplicationDTO.quantity());
        accountRepository.save(destinationAccount);

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
    public List<ClientLoanDTO> getClientLoans(String email) {
        Client client = clientRepository.findByEmail(email);
        if (client == null) {
            return List.of();
        }
        List<ClientLoan> clientLoans = clientLoanRepository.findByClient(client);
        return clientLoans.stream().map(ClientLoanDTO::new).collect(Collectors.toList());
    }
}

