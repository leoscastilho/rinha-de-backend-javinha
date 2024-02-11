package com.javinha.rinha.controller;

import java.util.List;

import com.javinha.rinha.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javinha.rinha.model.Clientes;
import com.javinha.rinha.repository.ClientesRepository;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

	@Autowired
	private ClientesService clientesService;


	@GetMapping
    public List<Clientes> findAll() {
        return clientesService.findAll();
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Clientes> findById(@PathVariable Long id){
		return clientesService
				.findById(id)
				.map(cliente -> ResponseEntity.ok().body(cliente))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
