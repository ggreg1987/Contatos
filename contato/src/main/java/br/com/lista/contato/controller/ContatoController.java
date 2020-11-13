package br.com.lista.contato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.lista.contato.model.Pessoa;
import br.com.lista.contato.repository.PessoaRepository;

@Controller
public class ContatoController {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping("/login")
	public ModelAndView index() {
		
		ModelAndView modelAndView = new ModelAndView("/cadastro/pessoa");
		
		return modelAndView;
		
	}
	
	

	@PostMapping("**/salvarpessoa")
	public ModelAndView salvar(Pessoa pessoa) {

		pessoaRepository.save(pessoa);
		ModelAndView modelAndView = new ModelAndView("/cadastro/pessoa");
		modelAndView.addObject("pessoaObjeto", new Pessoa());
		return modelAndView;

	}

}
