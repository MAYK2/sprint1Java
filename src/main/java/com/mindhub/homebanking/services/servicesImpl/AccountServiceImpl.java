package com.mindhub.homebanking.services.servicesImpl;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mindhub.homebanking.utils.RandomNumbers.eightDigits;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

   @Autowired
    private ClientService clientService;

    @Override
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOList = accounts.stream().map(AccountDTO::new).collect(Collectors.toList());
        if (!accounts.isEmpty()) {
            return new ResponseEntity<>(accountDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<AccountDTO> getAccountById(Long id) {
        Optional<Account> cuentaOptional = accountRepository.findById(id);
        if (cuentaOptional.isPresent()) {
            Account account = cuentaOptional.get();
            AccountDTO accountDTO = new AccountDTO(account);
            return new ResponseEntity<>(accountDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> createAccount(Authentication authentication) {
        Client client = clientService.getClientByEmail(authentication.getName());

        if (client.getAccounts().size() >= 3) {
            return new ResponseEntity<>("You reach the maximum limit of 3 accounts per client", HttpStatus.FORBIDDEN);
        }
        String numberAccount;
        do {
            int randomNumber = eightDigits();
            numberAccount = "VIN-" + String.format("%08d", randomNumber);
        } while (accountRepository.findByNumberAccount(numberAccount) != null);

        Account account = new Account(numberAccount, LocalDate.now(), 0.0);
        account.setClient(client);
        client.addAccount(account);

        clientService.saveClient(client);
        accountRepository.save(account);

        return new ResponseEntity<>("Client account created", HttpStatus.CREATED);
    }

    @Override
    public void SaveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account findByNumberAccount(String numberAccount) {
        return null;
    }

    @Override
    public ResponseEntity<List<AccountDTO>> getAccountsByClient(Authentication authentication) {
        Client client = clientService.getClientByEmail(authentication.getName());
        List<Account> accounts = new ArrayList<>(client.getAccounts());  // Convert Set<Account> to List<Account>
        List<AccountDTO> accountDTOList = accounts.stream().map(AccountDTO::new).collect(Collectors.toList());
        if (!accounts.isEmpty()) {
            return new ResponseEntity<>(accountDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
        }
    }

}
