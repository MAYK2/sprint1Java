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
    private LocalDateTime fecha;
    private TypeTransaction tipo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;


    public Transaction(Double amount, String description, LocalDateTime fecha, TypeTransaction tipo) {
        this.amount = amount;
        this.description = description;
        this.fecha = fecha;
        this.tipo = tipo;
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public TypeTransaction getTipo() {
        return tipo;
    }

    public void setTipo(TypeTransaction tipo) {
        this.tipo = tipo;
    }
}
