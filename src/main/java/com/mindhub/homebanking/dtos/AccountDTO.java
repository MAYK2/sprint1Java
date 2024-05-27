package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Account;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDTO {
    long id;
    private String numero;
    private LocalDate fechaCreacion;
    private Double saldo;
    private List<TransactionDTO> transacciones;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.numero = account.getNumero();
        this.fechaCreacion = account.getFechaCreacion();
        this.saldo = account.getSaldo();
        this.transacciones = account.getTransacciones().stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public Double getSaldo() {
        return saldo;
    }

    public List<TransactionDTO> getTransacciones() {
        return transacciones;
    }
}
