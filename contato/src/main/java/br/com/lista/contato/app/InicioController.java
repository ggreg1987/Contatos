package br.com.lista.contato.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InicioController {
	
	
	@RequestMapping("/")
	public String paginaInicial() {
		return "login";
	}
	
	

}
