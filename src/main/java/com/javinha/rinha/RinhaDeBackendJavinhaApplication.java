package com.javinha.rinha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {
		"com.javinha.rinha"
})
public class RinhaDeBackendJavinhaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RinhaDeBackendJavinhaApplication.class, args);
	}

}
