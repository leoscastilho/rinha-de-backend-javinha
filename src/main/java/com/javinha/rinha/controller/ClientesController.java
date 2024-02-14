package com.javinha.rinha.controller;


import com.javinha.rinha.exception.TransacaoException;
import com.javinha.rinha.record.PostTransacaoRequest;
import com.javinha.rinha.record.PostTransacaoResponse;
import com.javinha.rinha.service.ClientesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/clientes")
public class ClientesController {

	@Autowired
	private ClientesService clientesService;


	@PostMapping("/{id}/transacoes")
	public ResponseEntity<?> processaTransacao(@Valid @RequestBody PostTransacaoRequest postTransacaoRequest, @PathVariable Long id) {

		try{
			PostTransacaoResponse postTransacaoResponse = clientesService.processaTransacaoEAtualizaSaldo(postTransacaoRequest, id);
			return ResponseEntity.ok().body(postTransacaoResponse);
		} catch (ChangeSetPersister.NotFoundException e){
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		catch (TransacaoException e){
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage()); // Return HTTP 422
		}
		catch (Exception e){
				return new ResponseEntity<>("Erro interno do servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}/extrato")
	public ResponseEntity<?> getExtrato(@PathVariable Long id){
		try {
			return ResponseEntity.ok().body(clientesService.processaExtrato(id));

		}catch (ChangeSetPersister.NotFoundException e){
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e){
			return new ResponseEntity<>("Erro interno do servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
