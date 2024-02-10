package com.javinha.rinha.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javinha.rinha.model.Clientes;
import com.javinha.rinha.repository.ClientesRepository;

@RestController
@RequestMapping("/Clientes")
public class ClientesController {
	@Autowired
	private ClientesRepository clientesRepository;
	
	@GetMapping
    public List<Clientes> findAll() {
        return clientesRepository.findAll();
    }
	
	@GetMapping("/{id}")
	public Optional<Clientes> findById(@PathVariable Long id){
		return clientesRepository.findAllById(id);
	}
}
