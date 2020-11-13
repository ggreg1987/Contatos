package br.com.lista.contato.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.lista.contato.model.Pessoa;
import br.com.lista.contato.repository.PessoaRepository;

@Controller
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping("/login")
	public ModelAndView index() {

		ModelAndView modelAndView = new ModelAndView("/cadastro/pessoa");
		Iterable<Pessoa> listaPessoas = pessoaRepository.findAll();
		modelAndView.addObject("pessoas", listaPessoas);
		modelAndView.addObject("objpessoa", new Pessoa());

		return modelAndView;
	}

	@PostMapping("**/salvarpessoa")
	public ModelAndView salvarPessoa(Pessoa pessoa) {

		pessoaRepository.save(pessoa);
		ModelAndView modelAndView = new ModelAndView("/cadastro/pessoa");
		modelAndView.addObject("objpessoa", new Pessoa());
		Iterable<Pessoa> listaPessoas = pessoaRepository.findAll();
		modelAndView.addObject("pessoas", listaPessoas);

		return modelAndView;

	}

	@GetMapping("/editarpessoa/{idpessoa}")
	public ModelAndView editarPessoa(@PathVariable("idpessoa") Long idpessoa) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);

		ModelAndView modelAndView = new ModelAndView("/cadastro/pessoa");
		modelAndView.addObject("objpessoa", pessoa.get());

		return modelAndView;

	}
	
	@GetMapping("/excluirpessoa/{idpessoa}")
	public ModelAndView exluirPessoa(@PathVariable("idpessoa") Long idpessoa) {	
		
		pessoaRepository.deleteById(idpessoa);
		
		ModelAndView modelAndView = new ModelAndView("/cadastro/pessoa");
		modelAndView.addObject("pessoas", pessoaRepository.findAll());
		modelAndView.addObject("objpessoa", new Pessoa());
		
		return modelAndView;	
		
	}
	
	@PostMapping("/pesquisar")
	public ModelAndView pesquisarPessoa(@RequestParam("pesquisarpessoas") String pesquisarpessoas) {
		ModelAndView modelAndView = new ModelAndView("/cadastro/pessoa");
		modelAndView.addObject("pessoas", pessoaRepository.pesquisarPorNome(pesquisarpessoas));
		modelAndView.addObject("objpessoa", new Pessoa());
		
		return modelAndView;
		
	}
	
}
