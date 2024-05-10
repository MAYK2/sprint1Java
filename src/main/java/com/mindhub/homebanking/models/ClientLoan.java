package com.mindhub.homebanking.models;

import jakarta.persistence.*;

@Entity
public class ClientLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double quantity; // Cantidad solicitada
    private int payments;    // Pagos

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Client client; // Cliente asociado al préstamo

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;       // Préstamo asociado al cliente

    public ClientLoan() {
    }

    public ClientLoan(double quantity, int payments) {
        this.quantity = quantity;
        this.payments = payments;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public Client getCliente() {
        return client;
    }

    public void setCliente(Client client) {
        this.client = client;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

}
