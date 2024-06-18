package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Account;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDTO {
    long id;
    private String number;
    private LocalDate CreationDate;
    private Double balance;
    private List<TransactionDTO> transactions;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.number = account.getNumero();
        this.CreationDate = account.getCreationDate();
        this.balance = account.getBalance();
        this.transactions = account.getTransactions().stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getCreationDate() {
        return CreationDate;
    }

    public Double getBalance() {
        return balance;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }
}
