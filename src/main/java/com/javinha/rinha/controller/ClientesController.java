package com.javinha.rinha.controller;


import com.javinha.rinha.exception.SaldoException;
import com.javinha.rinha.record.PostTransacaoRequest;
import com.javinha.rinha.record.PostTransacaoResponse;
import com.javinha.rinha.service.ClientesService;
import jakarta.validation.Valid;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/clientes")
public class ClientesController {

	private final ClientesService clientesService;

	public ClientesController (ClientesService clientesService){
		this.clientesService = clientesService;
	}


	@PostMapping("/{id}/transacoes")
	public ResponseEntity<?> processaTransacao(@Valid @RequestBody PostTransacaoRequest postTransacaoRequest, @PathVariable Long id) throws SaldoException, ChangeSetPersister.NotFoundException {
			PostTransacaoResponse postTransacaoResponse = clientesService.processaTransacaoEAtualizaSaldo(postTransacaoRequest, id);
			return ResponseEntity.ok().body(postTransacaoResponse);
	}
	
	@GetMapping("/{id}/extrato")
	public ResponseEntity<?> getExtrato(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
			return ResponseEntity.ok().body(clientesService.processaExtrato(id));
	}
}
