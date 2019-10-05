package br.com.codenation.centralerros.repository;

import br.com.codenation.centralerros.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findByEmail(String email);

}
