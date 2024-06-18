package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOs = accounts.stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(accountDTOs);
    }

    public ResponseEntity<AccountDTO> getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElse(null);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new AccountDTO(account));
    }

    public ResponseEntity<List<AccountDTO>> getClientAccounts(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        List<Account> clientAccounts = accountRepository.findByClientUsername(username);
        List<AccountDTO> clientAccountDTOs = clientAccounts.stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(clientAccountDTOs);
    }

    public ResponseEntity<String> createAccount(Authentication authentication) {
        // Implementación de creación de cuenta aquí
        return ResponseEntity.ok("Account created successfully");
    }

    @Override
    public void SaveAccount(Account account) {

    }

    @Override
    public Account findByNumberAccount(String numberAccount) {
        return null;
    }
}
