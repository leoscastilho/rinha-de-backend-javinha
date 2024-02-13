package com.javinha.rinha.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.javinha.rinha.model.Clientes;


@Repository
public interface ClientesRepository extends JpaRepository<Clientes,Long>{
    @Modifying
    @Query("UPDATE Clientes c SET c.saldo = :saldo WHERE c.id = :id")
    void updateBalance (@Param("id") Long id, @Param("saldo") Integer saldo);
}
