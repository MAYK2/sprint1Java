package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.TransferRequestDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;

import java.util.List;

public interface TransactionService {
    void transfer(TransferRequestDTO transferRequest, Client client) throws Exception;
    List<Transaction> getTransactionsForClient(Client client);
}
