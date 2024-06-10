package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.dtos.CreateCardDTO;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CardService {
    List<Card> existingCards(Client currentClient, CardType cardType);
    void saveCard(Card card);
    List<CardDTO> getCards(Client currentClient);
    ResponseEntity<Object> createCard(Client currentClient, CreateCardDTO createCardDTO);
}
