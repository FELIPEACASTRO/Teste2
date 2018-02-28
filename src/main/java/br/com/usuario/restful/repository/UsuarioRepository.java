package br.com.usuario.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.usuario.restful.model.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

}
