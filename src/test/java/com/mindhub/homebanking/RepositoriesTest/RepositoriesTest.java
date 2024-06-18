package com.mindhub.homebanking.RepositoriesTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoriesTest {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountRepository accountRepository;
//    @Test
//    public void existLoans(){
//        List<Loan> loans = loanRepository.findAll();
//        assertThat(loans, is(not(empty())));
//    }
//
//    @Test
//    public void existPersonalLoan() {
//        List<Loan> loans = loanRepository.findAll();
//        assertThat(loans, hasItem(hasProperty("name", is("Personal"))));
//    }
//    // TransactionsRepository tests
//    @Test
//    public void existTransactions() {
//        List<Transaction> transactions = transactionsRepository.findAll();
//        assertThat(transactions, is(not(empty())));
//    }
//
//    @Test
//    public void existTransactionForAccountId() {
//        List<Transaction> transactions = transactionsRepository.findByAccountId(1L); // Assume 1L exists
//        assertThat(transactions, is(not(empty())));
//    }
//
//    // ClientRepository tests
//    @Test
//    public void existClients() {
//        List<Client> clients = clientRepository.findAll();
//        assertThat(clients, is(not(empty())));
//    }
//
//    @Test
//    public void existClientByEmail() {
//        Client client = clientRepository.findByEmail("melba@mindhub.com"); // Assume this email exists
//        assertThat(client, is(notNullValue()));
//    }
//
//    // CardRepository tests
//    @Test
//    public void existCards() {
//        List<Card> cards = cardRepository.findAll();
//        assertThat(cards, is(not(empty())));
//    }
//
//    @Test
//    public void existCardForClientAndType() {
//        Client client = clientRepository.findByEmail("mayco@admin.com"); // Assume this email exists
//        List<Card> cards = cardRepository.findByClientAndType(client, CardType.DEBIT);
//        assertThat(cards, is(not(empty())));
//    }
//
//    // AccountRepository tests
//    @Test
//    public void existAccounts() {
//        List<Account> accounts = accountRepository.findAll();
//        assertThat(accounts, is(not(empty())));
//    }
//
//    @Test
//    public void existAccountByNumber() {
//        Account account = accountRepository.findByNumberAccount("VIN001"); // Assume this account number exists
//        assertThat(account, is(notNullValue()));
//    }
}
