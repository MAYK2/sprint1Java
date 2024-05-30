package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomebankingApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository cuentaRepositorio,
                                      TransactionRepository transactionRepository, LoanRepository loanRepository,
                                      ClientLoanRepository clientLoanRepository, CardRepository cardRepository,
                                      PasswordEncoder passwordEncoder) {  // Inyección del PasswordEncoder
        return args -> {
            Client client = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("123"));  // Usar el PasswordEncoder
            Client client2 = new Client("Erick", "Guevara", "guevara@guevara.com", passwordEncoder.encode("456"));  // Usar el PasswordEncoder
            LocalDate today = LocalDate.now();
            LocalDate tomorrow = today.plusDays(1);
            LocalDateTime dateTime = LocalDateTime.of(2024, 5, 6, 15, 30, 0);
            LocalDate fiveYears = LocalDate.now().plusYears(5);

            // Crear las cuentas
            Account account1 = new Account("VIN001", today, 5000.0);
            Account account2 = new Account("VIN002", tomorrow, 7500.0);
            Account account3 = new Account("VIN003", tomorrow, 4500.0);
            Account account4 = new Account("VIN004", today, 4500.0);

            // Crear las transacciones
//            Transaction transaction1 = new Transaction(-5000.0, "Supermercado", dateTime, TypeTransaction.DEBITO);
//            Transaction transaction2 = new Transaction(-2500.0, "Kiosco", dateTime, TypeTransaction.DEBITO);
//            Transaction transaction3 = new Transaction(-1000.0, "Alimento", dateTime, TypeTransaction.DEBITO);
//            Transaction transaction4 = new Transaction(10000.0, "Steam", dateTime, TypeTransaction.CREDITO);
//            Transaction transaction5 = new Transaction(25000.0, "Zapatillas", dateTime, TypeTransaction.CREDITO);
//            Transaction transaction6 = new Transaction(35000.0, "Zapatos", dateTime, TypeTransaction.CREDITO);
//            Transaction transaction7 = new Transaction(-15000.0, "Gimnasio", dateTime, TypeTransaction.DEBITO);
//            Transaction transaction8 = new Transaction(-15000.0, "Alcohol", dateTime, TypeTransaction.DEBITO);
//            Transaction transaction9 = new Transaction(-15000.0, "MercadoPago", dateTime, TypeTransaction.DEBITO);
//            Transaction transaction10 = new Transaction(15000.0, "MercadoLibre", dateTime, TypeTransaction.CREDITO);
//            Transaction transaction11 = new Transaction(15000.0, "Neumaticos", dateTime, TypeTransaction.CREDITO);
//            Transaction transaction12 = new Transaction(-15000.0, "Café", dateTime, TypeTransaction.DEBITO);

            Loan hipotecario = new Loan("Hipotecario", 500000, List.of(12, 24, 36, 48, 60));
            Loan personal = new Loan("Personal", 100000, List.of(6, 12, 24));
            Loan automotor = new Loan("Automotor", 200000, List.of(6, 12, 24, 36));

            // Crear los ClientLoans
            ClientLoan melbaClientLoan = new ClientLoan(400000, 60);
            ClientLoan melbaClientLoan2 = new ClientLoan(50000, 12);

            ClientLoan erickClientLoan = new ClientLoan(100000, 24);
            ClientLoan erickClientLoan2 = new ClientLoan(200000, 36);

            // Crear las tarjetas
            Card cardMelbaDebit = new Card("4498-9824-1662-0586", 333, today, fiveYears, CreditCardType.DEBIT, ColorCard.GOLD, "Melba Morel");
            Card cardMelbaCredit = new Card("4498-9423-1152-5083", 550, today, fiveYears, CreditCardType.CREDIT, ColorCard.TITANIUM, "Melba Morel");
            Card cardErickDebit = new Card("4498-3770-3454-1222", 248, today, fiveYears, CreditCardType.DEBIT, ColorCard.SILVER, "Erick Guevara");

            // Guardar los clientes en el repositorio primero
            clientRepository.save(client);
            clientRepository.save(client2);

            // Guardar las cuentas en el repositorio
            cuentaRepositorio.save(account1);
            cuentaRepositorio.save(account2);
            cuentaRepositorio.save(account3);
            cuentaRepositorio.save(account4);

            // Asociar las cuentas con los clientes
            client.addAccount(account1);
            client.addAccount(account2);
            client2.addAccount(account3);
            client2.addAccount(account4);

            // Guardar las transacciones en el repositorio
//            transactionRepository.save(transaction1);
//            transactionRepository.save(transaction2);
//            transactionRepository.save(transaction3);
//            transactionRepository.save(transaction4);
//            transactionRepository.save(transaction5);
//            transactionRepository.save(transaction6);
//            transactionRepository.save(transaction7);
//            transactionRepository.save(transaction8);
//            transactionRepository.save(transaction9);
//            transactionRepository.save(transaction10);
//            transactionRepository.save(transaction11);
//            transactionRepository.save(transaction12);
//
//            account1.agregarTransaccion(transaction1);
//            account1.agregarTransaccion(transaction2);
//            account1.agregarTransaccion(transaction3);
//            account2.agregarTransaccion(transaction4);
//            account2.agregarTransaccion(transaction5);
//            account2.agregarTransaccion(transaction6);
//            account3.agregarTransaccion(transaction7);
//            account3.agregarTransaccion(transaction8);
//            account3.agregarTransaccion(transaction9);
//            account4.agregarTransaccion(transaction10);
//            account4.agregarTransaccion(transaction11);
//            account4.agregarTransaccion(transaction12);

            // Guardar los clientes actualizados en el repositorio
            clientRepository.save(client);
            clientRepository.save(client2);

            // Guardar los préstamos en el repositorio
            loanRepository.save(hipotecario);
            loanRepository.save(personal);
            loanRepository.save(automotor);

            client.addClientLoan(melbaClientLoan2);
            client.addClientLoan(melbaClientLoan);
            client2.addClientLoan(erickClientLoan2);
            client2.addClientLoan(erickClientLoan);
            personal.addClientLoan(melbaClientLoan2);
            hipotecario.addClientLoan(melbaClientLoan);
            automotor.addClientLoan(erickClientLoan2);
            personal.addClientLoan(erickClientLoan);

            // Guardar ClientLoans en el repositorio
            clientLoanRepository.save(melbaClientLoan);
            clientLoanRepository.save(melbaClientLoan2);
            clientLoanRepository.save(erickClientLoan);
            clientLoanRepository.save(erickClientLoan2);

            // Asociar los clientes con sus tarjetas
            client.addCard(cardMelbaDebit);
            client.addCard(cardMelbaCredit);
            client2.addCard(cardErickDebit);

            // Guardar las tarjetas en el repositorio
            cardRepository.save(cardMelbaDebit);
            cardRepository.save(cardMelbaCredit);
            cardRepository.save(cardErickDebit);

            // Guardar los clientes actualizados en el repositorio
            clientRepository.save(client);
            clientRepository.save(client2);
        };
    }
}
