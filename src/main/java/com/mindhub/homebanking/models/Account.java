package com.mindhub.homebanking.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String numberAccount;
    private LocalDate creationDate;
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Account() {
    }

    public Account(String numero, LocalDate creationDate, Double balance) {
        this.numberAccount = numero;
        this.creationDate = creationDate;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public String getNumero() {
        return numberAccount;
    }

    public void setNumero(String numero) {
        this.numberAccount = numero;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate fechaCreacion) {
        this.creationDate = fechaCreacion;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double saldo) {
        this.balance = saldo;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setCuenta(this);
    }

    public void setTransactions(List<Transaction> transacciones) {
        this.transactions = transacciones;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}

