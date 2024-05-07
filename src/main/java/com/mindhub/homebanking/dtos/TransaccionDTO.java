package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Cuenta;
import com.mindhub.homebanking.models.TipoTransaccion;
import com.mindhub.homebanking.models.Transaccion;

import java.time.LocalDateTime;

public class TransaccionDTO {
    private long id;
    private Double amount;
    private String description;
    private LocalDateTime fecha;
    private TipoTransaccion tipo;

    public TransaccionDTO(Transaccion transaccion) {
        this.id = transaccion.getId();
        this.amount = transaccion.getAmount();
        this.fecha = transaccion.getFecha();
        this.description = transaccion.getDescription();
        this.tipo = transaccion.getTipo();
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }
}
