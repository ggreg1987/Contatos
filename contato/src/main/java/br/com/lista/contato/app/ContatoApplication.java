package br.com.lista.contato.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "br.com.lista.contato.model")
public class ContatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContatoApplication.class, args);
	}

}
