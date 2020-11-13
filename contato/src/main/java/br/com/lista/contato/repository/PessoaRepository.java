package br.com.lista.contato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lista.contato.model.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
	
	
	@Query("SELECT p FROM Pessoa p WHERE p.nome like %?1%")
	public List<Pessoa> pesquisarPorNome(String nome);

}
