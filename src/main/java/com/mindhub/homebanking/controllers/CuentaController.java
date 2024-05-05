package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.Cuenta;
import com.mindhub.homebanking.repositorios.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;

    // Método para obtener todas las cuentas
    @GetMapping("/cuentas")
    public List<Cuenta> getCuentas() {
        return cuentaRepository.findAll();
    }

    // Método para obtener una cuenta por su ID
    @GetMapping("/cuentas/{id}")
    public Cuenta getCuentaById(@PathVariable Long id) {
        return cuentaRepository.findById(id).orElse(null);
    }
}
