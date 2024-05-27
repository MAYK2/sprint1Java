package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.ClientLoan;

public class ClientLoanDTO {
    private long id;
    private long loanId;
    private String loanName;
    private double quantity;
    private int payments;

    public ClientLoanDTO() {
    }
    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.loanId = clientLoan.getLoan().getId();
        this.loanName = clientLoan.getLoan().getName();
        this.quantity = clientLoan.getQuantity();
        this.payments = clientLoan.getPayments();
    }

    public long getLoanId() {
        return loanId;
    }

    public long getId() {
        return id;
    }

    public String getLoanName() {
        return loanName;
    }

    public double getQuantity() {
        return quantity;
    }

    public int getPayments() {
        return payments;
    }
}