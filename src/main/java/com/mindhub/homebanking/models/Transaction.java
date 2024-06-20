package com.mindhub.homebanking.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Double amount;
    private String description;
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;


    public Transaction(Double amount, String description, LocalDateTime date, TypeTransaction type) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.type = type;
    }

    public Transaction() {

    }
    public void setCuenta(Account account) {
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public TypeTransaction getType() {
        return type;
    }

    public void setType(TypeTransaction type) {
        this.type = type;
    }
}
