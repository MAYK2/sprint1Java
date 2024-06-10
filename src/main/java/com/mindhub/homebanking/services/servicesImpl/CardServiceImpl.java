package com.mindhub.homebanking.services.servicesImpl;

import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.dtos.CreateCardDTO;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ColorCard;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.services.CardService;
import com.mindhub.homebanking.utils.CardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.mindhub.homebanking.utils.RandomNumbers.generateCardNumber;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public void saveCard(Card card) {
        cardRepository.save(card);
    }

    @Override
    public List<Card> existingCards(Client currentClient, CardType cardType) {
        return cardRepository.findByClientAndType(currentClient, cardType);
    }

    @Override
    public List<CardDTO> getCards(Client currentClient) {
        return currentClient.getCards().stream().map(CardDTO::new).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Object> createCard(Client currentClient, CreateCardDTO createCardDTO) {
        CardType cardType;
        ColorCard cardColor;
        try {
            cardType = CardType.valueOf(createCardDTO.cardType().toUpperCase());
            cardColor = ColorCard.valueOf(createCardDTO.cardColor().toUpperCase());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid card type or color", HttpStatus.BAD_REQUEST);
        }

        List<Card> existingCards = existingCards(currentClient, cardType);
        if (existingCards.size() >= 3) {
            return new ResponseEntity<>("Client already has 3 cards of this type", HttpStatus.FORBIDDEN);
        }

        String cardNumber = CardUtils.generateCardNumber();
        int cvv = CardUtils.getCVV();
        LocalDate fromDate = LocalDate.now();
        LocalDate thruDate = fromDate.plusYears(5);
        String cardHolder = currentClient.getFirstName() + " " + currentClient.getLastName();

        Card card = new Card(cardNumber, cvv, fromDate, thruDate, cardType, cardColor, cardHolder);
        card.setClient(currentClient);

        saveCard(card);

        return new ResponseEntity<>("Card created", HttpStatus.CREATED);
    }
}