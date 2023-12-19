package br.com.rsds.crudspringcisco3905.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI CustomAPI() {
		return new OpenAPI().info(new Info().title("Swagger Telefones IP Cisco3905 - OpenAPI 3.0")
				.description("Esta é uma API de configuração de ramais em telefones IP modelo Cisco3905.")
				.contact(new Contact().email("ricardosantanadev4@gmail.com")).version("1.0.0"));
	}
}
