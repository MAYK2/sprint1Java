package com.mindhub.homebanking.ControllersTest;

import com.mindhub.homebanking.controllers.CardController;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.*;
import com.mindhub.homebanking.services.AuthService;
import com.mindhub.homebanking.services.CardService;
import com.mindhub.homebanking.services.ClientService;
import com.mindhub.homebanking.servicesSecurity.JwtUtilService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CardController.class)
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @MockBean
    private CardService cardService;
    @MockBean
    private AuthService authService;

    @MockBean
    private ClientRepository clientRepository;
    @MockBean
    JwtUtilService jwtUtilService;
    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private TransactionsRepository transactionsRepository;

    @MockBean
    private LoanRepository loanRepository;

    @MockBean
    private CardRepository cardRepository;

    @MockBean
    private ClientLoanRepository clientLoanRepository;

    @Autowired
    private WebApplicationContext context;


//    @Test
//    public void getCardsForClient_ReturnsOk() throws Exception {
//        // Simular la respuesta del servicio de tarjetas para el cliente Melba
//        Client melbaClient = new Client();
//        Set<Card> emptyCardSet = new HashSet<>();
//        melbaClient.setCards(emptyCardSet);// Simulamos que Melba no tiene tarjetas
//
//        // Configurar el comportamiento esperado de ClientService.getClientByEmail()
//        Mockito.when(clientService.getClientByEmail("melba@mindhub.com")).thenReturn(melbaClient);
//
//        // Realizar la solicitud GET al endpoint para obtener las tarjetas del cliente
//        ResultActions resultActions = mockMvc.perform(get("/api/clients/current/cards")
//                .contentType(MediaType.APPLICATION_JSON));
//
//        // Verificar que la solicitud fue exitosa (código de estado 200)
//        resultActions.andExpect(status().isOk());
//    }
}
