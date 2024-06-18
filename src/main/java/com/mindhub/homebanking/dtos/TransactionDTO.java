package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.TypeTransaction;
import com.mindhub.homebanking.models.Transaction;

import java.time.LocalDateTime;

public class TransactionDTO {
    private long id;
    private Double amount;
    private String description;
    private LocalDateTime date;
    private TypeTransaction type;

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.date = transaction.getDate();
        this.description = transaction.getDescription();
        this.type = transaction.getType();
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public TypeTransaction getType() {
        return type;
    }
}
