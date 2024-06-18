package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AccountService {
    ResponseEntity<List<AccountDTO>> getAllAccounts();
    ResponseEntity<AccountDTO> getAccountById(Long id);
    ResponseEntity<String> createAccount(Authentication authentication);
    void SaveAccount(Account account);
    Account findByNumberAccount(String numberAccount);
    ResponseEntity<List<AccountDTO>> getAccountsByClient(Authentication authentication);
}
