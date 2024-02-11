package com.javinha.rinha;

import com.javinha.rinha.enums.TransacaoTipo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RinhaDeBackendJavinhaApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void test (){
		String c = String.valueOf(TransacaoTipo.CREDITO);
	}

}
