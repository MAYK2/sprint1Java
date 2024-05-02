package com.mindhub.homebanking;


import com.mindhub.homebanking.models.Cliente;
import com.mindhub.homebanking.repositorios.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomebankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomebankingApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ClienteRepository clienteRepository) {
        return args -> {
            Cliente cliente = new Cliente("Melba", "Morel", "melba@mindhub.com");
            Cliente cliente2 = new Cliente("Erick", "Guevara", "guevara@guevara.com");

			clienteRepository.save(cliente);
			clienteRepository.save(cliente2);

			System.out.println(cliente);
			System.out.println(cliente2);
        };
    }
}
