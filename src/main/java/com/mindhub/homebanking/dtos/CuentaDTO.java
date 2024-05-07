package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Cuenta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CuentaDTO {
    long id;
    private String numero;
    private LocalDate fechaCreacion;
    private Double saldo;
    private List<TransaccionDTO> transacciones;

    public CuentaDTO(Cuenta cuenta) {
        this.id = cuenta.getId();
        this.numero = cuenta.getNumero();
        this.fechaCreacion = cuenta.getFechaCreacion();
        this.saldo = cuenta.getSaldo();
        this.transacciones = cuenta.getTransacciones().stream().map(TransaccionDTO::new).collect(Collectors.toList());
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
    public List<TransaccionDTO> getTransacciones() {
        return transacciones;
    }
}
