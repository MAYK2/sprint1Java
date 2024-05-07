package com.mindhub.homebanking.models;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cuenta> cuentas = new HashSet<>();
    public Cliente(String first, String last, String email) {
        this.firstName = first;
        this.lastName = last;
        this.email = email;
    }


    public Cliente() {

    }

    public Set<Cuenta> getCuentas() {
        return cuentas;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void addCuenta(Cuenta cuenta) {
        cuenta.setCliente(this);
        cuentas.add(cuenta);
    }
}
