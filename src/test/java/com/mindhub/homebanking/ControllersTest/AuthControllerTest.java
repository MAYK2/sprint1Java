package com.mindhub.homebanking.ControllersTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindhub.homebanking.controllers.AuthController;
import com.mindhub.homebanking.dtos.LoginDTO;
import com.mindhub.homebanking.repositories.*;
import com.mindhub.homebanking.services.AuthService;
import com.mindhub.homebanking.services.TransactionService;
import com.mindhub.homebanking.servicesSecurity.JwtUtilService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.naming.AuthenticationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private JwtUtilService jwtUtilService;

    @MockBean
    private ClientRepository clientRepository; // Agregar MockBean para ClientRepository

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

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

//    @Test
//    public void testLoginSuccess() throws Exception {
//        // Arrange
//        LoginDTO loginDTO = new LoginDTO("mayco@admin.com", "contraseña");
//        String jwtToken = "mocked-jwt-token";
//
//        Mockito.when(authService.login(Mockito.any(LoginDTO.class))).thenReturn(jwtToken);
//
//        // Act
//        MvcResult result = mockMvc.perform(post("/api/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(loginDTO)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        // Assert
//        String responseContent = result.getResponse().getContentAsString();
//        assertThat(responseContent).isEqualTo(jwtToken);
//    }
//
//
//    @Test
//    public void testLoginFailure() throws Exception {
//        // Arrange
//        LoginDTO loginDTO = new LoginDTO("mayco@admin.comx", "contraseña");
//
//        // Mock para simular credenciales inválidas
//        Mockito.when(authService.login(Mockito.any(LoginDTO.class)))
//                .thenThrow(new RuntimeException("Credenciales inválidas"));
//
//        // Act & Assert
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(loginDTO)))
//                .andExpect(status().isUnauthorized())
//                .andExpect(content().string("Credenciales inválidas"));
//    }
}
