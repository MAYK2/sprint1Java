package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Cliente;
import com.mindhub.homebanking.models.Cuenta;
import com.mindhub.homebanking.models.TipoTransaccion;
import com.mindhub.homebanking.models.Transaccion;
import com.mindhub.homebanking.repositorios.ClienteRepository;
import com.mindhub.homebanking.repositorios.CuentaRepository;
import com.mindhub.homebanking.repositorios.TransaccionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class HomebankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomebankingApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ClienteRepository clienteRepository, CuentaRepository cuentaRepositorio, TransaccionRepository transaccionRepository) {
        return args -> {
            Cliente cliente = new Cliente("Melba", "Morel", "melba@mindhub.com");
            Cliente cliente2 = new Cliente("Erick", "Guevara", "guevara@guevara.com");
            LocalDate today = LocalDate.now();
            LocalDate tomorrow = today.plusDays(1);
            LocalDateTime dateTime = LocalDateTime.of(2024, 5, 6, 15, 30, 0);
            // Crear las cuentas
            Cuenta cuenta1 = new Cuenta("VIN001", today, 5000.0);
            Cuenta cuenta2 = new Cuenta("VIN002", tomorrow, 7500.0);
            Cuenta cuenta3 = new Cuenta("VIN003", tomorrow, 4500.0);
            Cuenta cuenta4 = new Cuenta("VIN004", today, 4500.0);

            //Crear las transacciones
            Transaccion transaccion1 = new Transaccion(-5000.0, "Supermercado", dateTime, TipoTransaccion.DEBITO);
            Transaccion transaccion2 = new Transaccion(-2500.0, "Kiosco", dateTime, TipoTransaccion.DEBITO);
            Transaccion transaccion3 = new Transaccion(-1000.0, "Alimento", dateTime, TipoTransaccion.DEBITO);
            Transaccion transaccion4 = new Transaccion(10000.0, "Steam", dateTime, TipoTransaccion.CREDITO);
            Transaccion transaccion5 = new Transaccion(25000.0, "Zapatillas", dateTime, TipoTransaccion.CREDITO);
            Transaccion transaccion6 = new Transaccion(35000.0, "Zapatos", dateTime, TipoTransaccion.CREDITO);
            Transaccion transaccion7 = new Transaccion(-15000.0, "Gimnasio", dateTime, TipoTransaccion.DEBITO);
            Transaccion transaccion8 = new Transaccion(-15000.0, "Alcohol", dateTime, TipoTransaccion.DEBITO);
            Transaccion transaccion9 = new Transaccion(-15000.0, "MercadoPago", dateTime, TipoTransaccion.DEBITO);
            Transaccion transaccion10 = new Transaccion(15000.0, "MercadoLibre", dateTime, TipoTransaccion.CREDITO);
            Transaccion transaccion11 = new Transaccion(15000.0, "Neumaticos", dateTime, TipoTransaccion.CREDITO);
            Transaccion transaccion12 = new Transaccion(-15000.0, "Café", dateTime, TipoTransaccion.DEBITO);

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

            // Guardar las transacciones en el repositorio
            transaccionRepository.save(transaccion1);
            transaccionRepository.save(transaccion2);
            transaccionRepository.save(transaccion3);
            transaccionRepository.save(transaccion4);
            transaccionRepository.save(transaccion5);
            transaccionRepository.save(transaccion6);
            transaccionRepository.save(transaccion7);
            transaccionRepository.save(transaccion8);
            transaccionRepository.save(transaccion9);
            transaccionRepository.save(transaccion10);
            transaccionRepository.save(transaccion11);
            transaccionRepository.save(transaccion12);

            cuenta1.agregarTransaccion(transaccion1);
            cuenta1.agregarTransaccion(transaccion2);
            cuenta1.agregarTransaccion(transaccion3);
            cuenta2.agregarTransaccion(transaccion4);
            cuenta2.agregarTransaccion(transaccion5);
            cuenta2.agregarTransaccion(transaccion6);
            cuenta3.agregarTransaccion(transaccion7);
            cuenta3.agregarTransaccion(transaccion8);
            cuenta3.agregarTransaccion(transaccion9);
            cuenta4.agregarTransaccion(transaccion10);
            cuenta4.agregarTransaccion(transaccion11);
            cuenta4.agregarTransaccion(transaccion12);


            // Guardar los clientes actualizados en el repositorio
            clienteRepository.save(cliente);
            clienteRepository.save(cliente2);
        };
    }

}
