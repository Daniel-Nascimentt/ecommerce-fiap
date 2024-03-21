package br.com.fiap.gateway.msgateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsGatewayApplication {

	@Value("${host.ms.usuarios}")
	private String hostMsUsuarios;
	@Value("${host.ms.gt.itens}")
	private String hostMsGtItens;
	@Value("${host.ms.carrinho}")
	private String hostMsCarrinho;
	@Value("${host.ms.pagamentos}")
	private String hostMsPagamentos;
	@Value("${host.ms.gt.autenticacao}")
	private String hostMsAutenticacao;

	public static void main(String[] args) {
		SpringApplication.run(MsGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route(r -> r.path("/ms-cliente/**").uri(this.hostMsUsuarios))
				.route(r -> r.path("/ms-quartos/**").uri(this.hostMsGtItens))
				.route(r -> r.path("/ms-reservas/**").uri(this.hostMsCarrinho))
				.route(r -> r.path("/ms-servicos/**").uri(this.hostMsPagamentos))
				.route(r -> r.path("/ms-servicos/**").uri(this.hostMsAutenticacao))
				.build();
	}

}
