package com.mindhub.homebanking.servicesSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mindhub.homebanking.dtos.TransferRequestDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TypeTransaction;
import com.mindhub.homebanking.repositorios.AccountRepository;
import com.mindhub.homebanking.repositorios.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void transfer(TransferRequestDTO transferRequest, Client client) throws Exception {
        // Verificación de parámetros vacíos
        if (transferRequest.fromAccountNumber() == null || transferRequest.toAccountNumber() == null ||
                transferRequest.amount() == null || transferRequest.description() == null) {
            throw new Exception("Todos los parámetros son obligatorios");
        }

        // Verificación de que los números de cuenta no sean los mismos
        if (transferRequest.fromAccountNumber().equals(transferRequest.toAccountNumber())) {
            throw new Exception("Los números de cuenta no pueden ser los mismos");
        }

        // Verificación de que la cuenta de origen exista
        Account fromAccount = accountRepository.findByNumberAccount(transferRequest.fromAccountNumber());
        if (fromAccount == null) {
            throw new Exception("La cuenta de origen no existe");
        }

        // Verificación de que la cuenta de destino exista
        Account toAccount = accountRepository.findByNumberAccount(transferRequest.toAccountNumber());
        if (toAccount == null) {
            throw new Exception("La cuenta de destino no existe");
        }

        // Verificación de que la cuenta de origen pertenezca al cliente autenticado
        if (!fromAccount.getClient().equals(client)) {
            throw new Exception("La cuenta de origen no pertenece al cliente autenticado");
        }

        // Verificación de que la cuenta de origen tenga el monto disponible
        if (fromAccount.getSaldo() < transferRequest.amount()) {
            throw new Exception("Saldo insuficiente en la cuenta de origen");
        }

        // Actualizar el saldo de la cuenta de origen y crear la transacción de débito
        fromAccount.setSaldo(fromAccount.getSaldo() - transferRequest.amount());
        Transaction debitTransaction = new Transaction(-transferRequest.amount(), transferRequest.description() + " " + transferRequest.toAccountNumber(), LocalDateTime.now(), TypeTransaction.DEBIT);
        debitTransaction.setCuenta(fromAccount);
        transactionRepository.save(debitTransaction);

        // Actualizar el saldo de la cuenta de destino y crear la transacción de crédito
        toAccount.setSaldo(toAccount.getSaldo() + transferRequest.amount());
        Transaction creditTransaction = new Transaction(transferRequest.amount(), transferRequest.description() + " " + transferRequest.fromAccountNumber(), LocalDateTime.now(), TypeTransaction.CREDIT);
        creditTransaction.setCuenta(toAccount);
        transactionRepository.save(creditTransaction);

        // Guardar las cuentas actualizadas
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);


    }
    public List<Transaction> getTransactionsForClient(Client client) {
        return client.getAccounts().stream()
                .flatMap(account -> account.getTransacciones().stream())
                .collect(Collectors.toList());
    }
}
