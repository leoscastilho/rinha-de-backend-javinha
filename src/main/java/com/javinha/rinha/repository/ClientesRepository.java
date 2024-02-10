package com.javinha.rinha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javinha.rinha.model.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes,Long>{
	List<Clientes> findAll();
}
