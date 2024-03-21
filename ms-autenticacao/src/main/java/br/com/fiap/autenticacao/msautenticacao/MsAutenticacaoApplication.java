package br.com.fiap.autenticacao.msautenticacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsAutenticacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAutenticacaoApplication.class, args);
	}

}
