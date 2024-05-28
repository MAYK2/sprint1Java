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
    private LocalDate fechaCreacion;
    private Double saldo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transacciones = new ArrayList<>();

    public Account() {
    }

    public Account(String numero, LocalDate fechaCreacion, Double saldo) {
        this.numberAccount = numero;
        this.fechaCreacion = fechaCreacion;
        this.saldo = saldo;
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

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getCliente() {
        return client;
    }

    public void agregarTransaccion(Transaction transaction) {
        transacciones.add(transaction);
        transaction.setCuenta(this);
    }

    public void setTransacciones(List<Transaction> transacciones) {
        this.transacciones = transacciones;
    }

    public List<Transaction> getTransacciones() {
        return transacciones;
    }
}

