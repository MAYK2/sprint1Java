package com.mindhub.homebanking.repositorios;
import com.mindhub.homebanking.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
