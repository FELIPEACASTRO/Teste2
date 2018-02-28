package br.com.usuario.restful.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.usuario.restful.model.Usuario;
import br.com.usuario.restful.repository.UsuarioRepository;

@Service
public class DefaultUsuarioService implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario save(Usuario entity) {
		return usuarioRepository.save(entity);
	}

	@Override
	public Usuario getById(Serializable id) {
		return usuarioRepository.findOne((Long) id);
	}

	@Override
	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		usuarioRepository.delete((Long) id);
	}


}
