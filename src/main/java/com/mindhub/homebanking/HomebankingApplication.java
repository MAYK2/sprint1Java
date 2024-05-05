package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Cliente;
import com.mindhub.homebanking.models.Cuenta;
import com.mindhub.homebanking.repositorios.ClienteRepository;
import com.mindhub.homebanking.repositorios.CuentaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class HomebankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomebankingApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ClienteRepository clienteRepository, CuentaRepository cuentaRepositorio) {
        return args -> {
            Cliente cliente = new Cliente("Melba", "Morel", "melba@mindhub.com");
            Cliente cliente2 = new Cliente("Erick", "Guevara", "guevara@guevara.com");
            LocalDate today = LocalDate.now();
            LocalDate tomorrow = today.plusDays(1);

            // Crear las cuentas
            Cuenta cuenta1 = new Cuenta("VIN001", today, 5000.0);
            Cuenta cuenta2 = new Cuenta("VIN002", tomorrow, 7500.0);
            Cuenta cuenta3 = new Cuenta("VIN003", tomorrow, 4500.0);
            Cuenta cuenta4 = new Cuenta("VIN004", today, 4500.0);

            // Guardar los clientes en el repositorio primero
            clienteRepository.save(cliente);
            clienteRepository.save(cliente2);

            // Guardar las cuentas en el repositorio
            cuentaRepositorio.save(cuenta1);
            cuentaRepositorio.save(cuenta2);
            cuentaRepositorio.save(cuenta3);
            cuentaRepositorio.save(cuenta4);

            // Asociar las cuentas con los clientes
            cliente.addCuenta(cuenta1);
            cliente.addCuenta(cuenta2);
            cliente2.addCuenta(cuenta3);
            cliente2.addCuenta(cuenta4);

            // Guardar los clientes actualizados en el repositorio
            clienteRepository.save(cliente);
            clienteRepository.save(cliente2);
        };
    }

}
