//package com.mindhub.homebanking.ControllersTest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mindhub.homebanking.controllers.TransactionController;
//import com.mindhub.homebanking.dtos.TransferRequestDTO;
//import com.mindhub.homebanking.models.Client;
//import com.mindhub.homebanking.repositories.*;
//import com.mindhub.homebanking.services.AuthService;
//import com.mindhub.homebanking.services.ClientService;
//import com.mindhub.homebanking.services.TransactionService;
//import com.mindhub.homebanking.servicesSecurity.JwtUtilService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doNothing;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class TransactionControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//    @MockBean
//    private AuthService authService;
//
//    @MockBean
//    private JwtUtilService jwtUtilService;
//
//    @MockBean
//    private ClientRepository clientRepository; // Agregar MockBean para ClientRepository
//
//    @MockBean
//    private AccountRepository accountRepository;
//
//    @MockBean
//    private TransactionsRepository transactionsRepository;
//
//    @MockBean
//    private LoanRepository loanRepository;
//
//    @MockBean
//    private CardRepository cardRepository;
//
//    @MockBean
//    private ClientLoanRepository clientLoanRepository;
//    @BeforeEach
//    public void setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(new TransactionController()).build();
//    }
//    @Test
//    @WithMockUser(username = "melba@mindhub.com", roles = {"CLIENT"})
//    public void transfer_SuccessfulTransfer_ReturnsOk() throws Exception {
//        // Datos de la transferencia
//        TransferRequestDTO transferRequestDTO = new TransferRequestDTO("VIN001", "VIN002", 100.0, "Transferencia de prueba");
//
//        // Convertir el objeto TransferRequestDTO a JSON
//        String requestBody = objectMapper.writeValueAsString(transferRequestDTO);
//
//        // Realizar la solicitud POST al endpoint de transferencia
//        ResultActions resultActions = mockMvc.perform(post("/api/transfer")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody));
//
//        // Verificar que la solicitud fue exitosa (código de estado 200)
//        resultActions.andExpect(status().isOk());
//    }
//}