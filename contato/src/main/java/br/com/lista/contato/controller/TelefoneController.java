package br.com.lista.contato.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.lista.contato.model.Pessoa;
import br.com.lista.contato.model.Telefone;
import br.com.lista.contato.repository.PessoaRepository;
import br.com.lista.contato.repository.TelefoneRepository;

public class TelefoneController {
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	@PostMapping("**/salvarfone/{pessoaid}")
	public ModelAndView salvarTelefone(@PathVariable("pessoaid") Long pessoaid, Telefone telefone) {
		Pessoa pessoa = pessoaRepository.findById(pessoaid).get();
		
		if (telefone != null && telefone.getNumero().isEmpty() || telefone.getTipo().isEmpty()) {
			
			ModelAndView modelAndView = new ModelAndView("/cadastro/telefones");
			modelAndView.addObject("objpessoa", pessoa);
			modelAndView.addObject("telefones", telefoneRepository.listaTelefone(pessoaid));
			
			List<String> msg = new ArrayList<String>();
			if(telefone.getNumero().isEmpty()) {
				msg.add("Digite um número");
			}
			
			if(telefone.getTipo().isEmpty()) {
				msg.add("Digite um Tipo");
			}
			
			modelAndView.addObject("msg", msg);
			
			return modelAndView;
		} 
		telefone.setPessoa(pessoa);
		telefoneRepository.save(telefone);
		ModelAndView modelAndView = new ModelAndView("/cadastro/telefones");
		modelAndView.addObject("objpessoa", pessoa);
		modelAndView.addObject("telefones", telefoneRepository.listaTelefone(pessoaid));
		
		return modelAndView;
		
	}
	
	@GetMapping("/excluirfone/{idfone}")
	public ModelAndView excluirFone(@PathVariable("idfone") Long idfone) {
		Pessoa pessoa = telefoneRepository.findById(idfone).get().getPessoa();

		telefoneRepository.deleteById(idfone);

		ModelAndView modelAndView = new ModelAndView("/cadastro/telefones");
		modelAndView.addObject("objpessoa", pessoa);
		modelAndView.addObject("telefones", telefoneRepository.listaTelefone(pessoa.getId()));

		return modelAndView;

	}

}
