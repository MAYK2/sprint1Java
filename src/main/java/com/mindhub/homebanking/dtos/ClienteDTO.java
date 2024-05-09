package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<CuentaDTO> cuentas;
    private List<ClientLoanDTO> loans;


    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.firstName = cliente.getFirstName();
        this.lastName = cliente.getLastName();
        this.email = cliente.getEmail();
        // Mapear las cuentas del cliente a DTOs de cuentas
       // this.cuentas = cliente.getCuentas().stream().map(CuentaDTO::new).collect(Collectors.toList());
        this.loans = cliente.getClientLoans().stream().map(ClientLoanDTO::new).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<CuentaDTO> getCuentas() {
        return cuentas;
    }
    public List<ClientLoanDTO> getLoans() {
        return loans;
    }

}
