package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Cuenta;

import java.time.LocalDate;

public class CuentaDTO {
    long id;
    private String numero;
    private LocalDate fechaCreacion;
    private Double saldo;

    public CuentaDTO(Cuenta cuenta) {
        this.id = cuenta.getId();
        this.numero = cuenta.getNumero();
        this.fechaCreacion = cuenta.getFechaCreacion();
        this.saldo = cuenta.getSaldo();
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
}
