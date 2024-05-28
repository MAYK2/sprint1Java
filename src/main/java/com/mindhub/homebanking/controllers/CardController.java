package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dtos.CreateCardDTO;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ColorCard;
import com.mindhub.homebanking.models.CreditCardType;
import com.mindhub.homebanking.repositorios.CardRepository;
import com.mindhub.homebanking.repositorios.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(Authentication authentication, @RequestBody CreateCardDTO createCardDTO) {
        Client currentClient = clientRepository.findByEmail(authentication.getName());
        if (currentClient == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.FORBIDDEN);
        }

        CreditCardType cardType;
        ColorCard cardColor;
        try {
            cardType = CreditCardType.valueOf(createCardDTO.cardType().toUpperCase());
            cardColor = ColorCard.valueOf(createCardDTO.cardColor().toUpperCase());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid card type or color", HttpStatus.BAD_REQUEST);
        }

        List<Card> existingCards = cardRepository.findByClientAndType(currentClient, cardType);
        if (existingCards.size() >= 3) {
            return new ResponseEntity<>("Client already has 3 cards of this type", HttpStatus.FORBIDDEN);
        }

        String cardNumber = generateCardNumber();
        int cvv = new Random().nextInt(900) + 100;
        LocalDate fromDate = LocalDate.now();
        LocalDate thruDate = fromDate.plusYears(5);
        String cardHolder = currentClient.getFirstName() + " " + currentClient.getLastName();

        Card card = new Card(cardNumber, cvv, fromDate, thruDate, cardType, cardColor, cardHolder);
        card.setClient(currentClient);

        cardRepository.save(card);

        return new ResponseEntity<>("Card created", HttpStatus.CREATED);
    }

    private String generateCardNumber() {
        Random random = new Random();
        return String.format("%04d-%04d-%04d-%04d",
                random.nextInt(10000),
                random.nextInt(10000),
                random.nextInt(10000),
                random.nextInt(10000));
    }
}
