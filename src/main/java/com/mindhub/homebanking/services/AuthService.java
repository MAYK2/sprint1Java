package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.LoginDTO;
import com.mindhub.homebanking.dtos.RegisterDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    String login(LoginDTO loginDTO);
    ResponseEntity<?> register(RegisterDTO registerDTO);
    ResponseEntity<?> getClient(String email);
}
