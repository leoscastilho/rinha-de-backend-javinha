package com.javinha.rinha.repository;

import com.javinha.rinha.model.Saldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaldoRepository extends JpaRepository<Saldo, Long> {

    Optional<Saldo> findByClientes_Id (Long clienteId);
}
