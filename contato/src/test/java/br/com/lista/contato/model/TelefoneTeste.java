package br.com.lista.contato.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.lista.contato.app.ContatoApplication;
import br.com.lista.contato.repository.TelefoneRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = ContatoApplication.class)
class TelefoneTeste {
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@Test
	public void salvar() {
		
		Telefone novoTel = new Telefone("8199389989", "celular");
		telefoneRepository.save(novoTel);
		
	}

}
