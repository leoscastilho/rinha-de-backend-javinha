package com.javinha.rinha.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance")
public class FinanceController {

	@GetMapping("/ping")
	public ResponseEntity<String> get(){
		return ResponseEntity.ok("Pong");
	}
}
