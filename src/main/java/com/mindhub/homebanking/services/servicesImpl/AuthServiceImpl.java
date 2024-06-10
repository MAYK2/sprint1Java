package com.mindhub.homebanking.services.servicesImpl;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.dtos.LoginDTO;
import com.mindhub.homebanking.dtos.RegisterDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.AuthService;
import com.mindhub.homebanking.services.ClientService;
import com.mindhub.homebanking.servicesSecurity.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.mindhub.homebanking.utils.RandomNumbers.eightDigits;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService; //*

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Override
    public String login(LoginDTO loginDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.email());
            return jwtUtilService.generateToken(userDetails);
        } catch (Exception e) {
            throw new RuntimeException("Email or password invalid");
        }
    }

    @Override
    public ResponseEntity<?> register(RegisterDTO registerDTO) {
        if (clientService.getClientByEmail(registerDTO.email()) != null) {
            return new ResponseEntity<>("Email is already registered", HttpStatus.FORBIDDEN);
        }
        if (registerDTO.firstName().isBlank()) {
            return new ResponseEntity<>("The name field must not be empty", HttpStatus.BAD_REQUEST);
        }
        if (registerDTO.lastName().isBlank()) {
            return new ResponseEntity<>("The last name field must not be empty", HttpStatus.BAD_REQUEST);
        }
        if (registerDTO.email().isBlank()) {
            return new ResponseEntity<>("The email field must not be empty", HttpStatus.BAD_REQUEST);
        }
        if (registerDTO.password().isBlank()) {
            return new ResponseEntity<>("The password field must not be empty", HttpStatus.BAD_REQUEST);
        }
        if (registerDTO.password().length() < 8) {
            return new ResponseEntity<>("The password must have at least 8 characters", HttpStatus.BAD_REQUEST);
        }
        if (clientService.existEmail(registerDTO.email())) {
            return new ResponseEntity<>("Email is already registered", HttpStatus.FORBIDDEN);
        }

        Client client = new Client(
                registerDTO.firstName(),
                registerDTO.lastName(), registerDTO.email(),
                passwordEncoder.encode(registerDTO.password()));

        String number;
        do {
            number = "VIN-" + eightDigits();
        } while (accountService.findByNumberAccount(number) != null);

        Account account = new Account(number, LocalDate.now(), 0.0);
        account.setClient(client);
        client.addAccount(account);
        clientService.saveClient(client);
        accountService.SaveAccount(account);

        return new ResponseEntity<>("Client created", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getClient(String email) {
        Client client = clientService.getClientByEmail(email);
        return ResponseEntity.ok(new ClientDTO(client));
    }
}
