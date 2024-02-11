package com.javinha.rinha.service;

import com.javinha.rinha.enums.TransacaoTipo;
import com.javinha.rinha.model.Clientes;
import com.javinha.rinha.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {

    @Autowired
    ClientesRepository clientesRepository;


    public List<Clientes> findAll(){
        return clientesRepository.findAll();
    }

    public Optional<Clientes> findById (Long Id){
        return clientesRepository
                .findById(Id);
    }
}
