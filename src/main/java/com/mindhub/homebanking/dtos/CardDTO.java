package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.ColorCard;
import com.mindhub.homebanking.models.CardType;

import java.time.LocalDate;

public class CardDTO {
    private long id;
    private String number;
    private LocalDate thrudate;
    private LocalDate fromdate;
    private int cvv;
    private CardType Type;
    private ColorCard color;
    private String cardholder;

    public CardDTO(Card card) {
        this.id = card.getId();
        this.number = card.getNumber();
        this.thrudate = card.getThrudate();
        this.fromdate = card.getFromdate();
        this.cvv = card.getCvv();
        this.Type = card.getType();
        this.color = card.getColor();
        this.cardholder = card.getCardholder();
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getFromdate() {
        return fromdate;
    }

    public LocalDate getThrudate() {
        return thrudate;
    }

    public int getCvv() {
        return cvv;
    }

    public ColorCard getColor() {
        return color;
    }

    public CardType getType() {
        return Type;
    }

    public String getCardholder() {
        return cardholder;
    }
}
