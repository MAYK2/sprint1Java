package com.mindhub.homebanking.models;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String number;
    private LocalDate thrudate;
    private LocalDate fromdate;
    private int cvv;
    private CreditCardType Type;
    private ColorCard color;
    private String cardholder;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    Client client;

    public Card() {
    }

    public Card(String number, int cvv, LocalDate fromdate, LocalDate thrudate, CreditCardType type, ColorCard color, String cardholder) {
        this.number = number;
        this.cvv = cvv;
        this.fromdate = fromdate;
        this.thrudate = thrudate;
        Type = type;
        this.color = color;
        this.cardholder = cardholder;
    }

    public long getId() {
        return id;
    }

    public LocalDate getThrudate() {
        return thrudate;
    }

    public void setThrudate(LocalDate thrudate) {
        this.thrudate = thrudate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getFromdate() {
        return fromdate;
    }

    public void setFromdate(LocalDate fromdate) {
        this.fromdate = fromdate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public CreditCardType getType() {
        return Type;
    }

    public void setType(CreditCardType type) {
        Type = type;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public ColorCard getColor() {
        return color;
    }

    public void setColor(ColorCard color) {
        this.color = color;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
