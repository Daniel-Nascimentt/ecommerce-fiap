package br.com.fiap.msCarrinho.msCarrinho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsCarrinhoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCarrinhoApplication.class, args);
	}

}
