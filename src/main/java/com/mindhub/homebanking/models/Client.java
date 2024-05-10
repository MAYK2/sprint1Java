package com.mindhub.homebanking.models;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ClientLoan> clientLoans = new HashSet<>();

    @OneToMany (mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Card> cards = new HashSet<>();

    public Client(String first, String last, String email) {
        this.firstName = first;
        this.lastName = last;
        this.email = email;
    }

    public Client() {

    }

    public Set<Account> getCuentas() {
        return accounts;
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


    public void setClientLoans(Set<ClientLoan> clientLoans) {
        this.clientLoans = clientLoans;
    }

    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public void addCuenta(Account account) {
        account.setCliente(this);
        accounts.add(account);
    }

    public void addClientLoan(ClientLoan clientLoan) {
        clientLoan.setCliente(this);
        clientLoans.add(clientLoan);
    }
    public List<Loan> getLoans() {
        return clientLoans.stream().map(client -> client.getLoan()).collect(Collectors.toList());
    }

    public void addCard(Card card) {
        card.setClient(this);
        cards.add(card);
    }
}