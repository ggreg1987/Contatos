package br.com.lista.contato.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lista.contato.model.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	@Query("SELECT u FROM Usuario u WHERE u.login = ?1")
	public Usuario procurarLogin(String login);

}
