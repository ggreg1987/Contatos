package br.com.lista.contato.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.lista.contato.model.Pessoa;
import br.com.lista.contato.model.Telefone;
import br.com.lista.contato.repository.PessoaRepository;
import br.com.lista.contato.repository.TelefoneRepository;

@Controller
public class ContatoController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private TelefoneRepository telefoneRepository;


	@GetMapping("/login")
	public ModelAndView index() {

		ModelAndView modelAndView = new ModelAndView("/cadastro/pessoa");
		Iterable<Pessoa> listaPessoas = pessoaRepository.findAll();
		modelAndView.addObject("pessoas", listaPessoas);
		modelAndView.addObject("objpessoa", new Pessoa());

		return modelAndView;
	}

	@PostMapping("/salvarpessoa")
	public ModelAndView salvarPessoa(Pessoa pessoa) {
		ModelAndView modelAndView = new ModelAndView("/cadastro/pessoa");

		if (pessoa != null && pessoa.getNome().isEmpty() || pessoa.getIdade() == null) {
			
			modelAndView.addObject("objpessoa", new Pessoa());
			Iterable<Pessoa> listaPessoas = pessoaRepository.findAll();
			modelAndView.addObject("pessoas", listaPessoas);
			
			
			List<String> msg = new ArrayList<String>();
			
			if(pessoa.getNome().isEmpty()) {
				msg.add("Digite um Nome ");
			
			} 
			
			if(pessoa.getIdade() == null) {
				msg.add("Digite a Idade");
			}
			modelAndView.addObject("msg", msg);
			return modelAndView;		
		}
		

			pessoaRepository.save(pessoa);

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

	@GetMapping("/telefones/{idpessoa}")
	public ModelAndView telaTelefone(@PathVariable("idpessoa") Long idpessoa) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);

		ModelAndView modelAndView = new ModelAndView("/cadastro/telefones");
		modelAndView.addObject("objpessoa", pessoa.get());
		modelAndView.addObject("telefones", telefoneRepository.listaTelefone(idpessoa));

		return modelAndView;

	}

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
