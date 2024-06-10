package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.dtos.CreateCardDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.services.CardService;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CardController {


    @Autowired
    private ClientService clientService;
    @Autowired
    private CardService cardService;


    @GetMapping("/clients/current/cards")
    public ResponseEntity<?> getCards(Authentication authentication) {
        Client currentClient = clientService.getClientByEmail(authentication.getName());
        if (currentClient == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.FORBIDDEN);
        }
        List<CardDTO> cards = cardService.getCards(currentClient);
        if (cards.isEmpty()) {
            return new ResponseEntity<>("Client has no cards", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(Authentication authentication, @RequestBody CreateCardDTO createCardDTO) {
        Client currentClient = clientService.getClientByEmail(authentication.getName());
        if (currentClient == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.FORBIDDEN);
        }
        return cardService.createCard(currentClient, createCardDTO);
    }
}
