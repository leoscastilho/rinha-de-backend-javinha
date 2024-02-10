package com.javinha.rinha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javinha.rinha.model.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes,Long>{
	List<Clientes> findAllById(Long id);

}
