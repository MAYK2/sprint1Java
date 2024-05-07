package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.CuentaDTO;
import com.mindhub.homebanking.models.Cuenta;
import com.mindhub.homebanking.repositorios.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;

    // Endpoint para obtener todas las cuentas
    @GetMapping("/cuentas")
    public ResponseEntity<?> getAllAccounts() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        List<CuentaDTO> cuentaDTOList = cuentas.stream().map(CuentaDTO::new).collect(Collectors.toList());
        if (!cuentas.isEmpty()) {
            return new ResponseEntity<>(cuentaDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se han encontrado cuentas", HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para buscar una cuenta por su ID
    @GetMapping("/cuentas/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Long id) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
        if (cuentaOptional.isPresent()) {
            Cuenta cuenta = cuentaOptional.get();
            CuentaDTO cuentaDTO = new CuentaDTO(cuenta);
            return new ResponseEntity<>(cuentaDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cuenta no encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
