package br.com.usuario.restful.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.usuario.restful.model.Usuario;
import br.com.usuario.restful.service.UsuarioService;



@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	final static Logger logger = Logger.getLogger(UsuarioController.class);

	@Autowired
	UsuarioService userService;


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
		userService.save(usuario);
		logger.debug("Added:: " + usuario);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
	}


	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateUsuario(@RequestBody Usuario usuario) {
		Usuario existinguser = userService.getById(usuario.getId());
		if (existinguser == null) {
			logger.debug("Usuario id " + usuario.getId() + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			userService.save(usuario);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> getUsuario(@PathVariable("id") Long id) {
		Usuario usuario = userService.getById(id);
		if (usuario == null) {
			logger.debug("Usuario id " + id + " does not exists");
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Usuario:: " + usuario);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> getAllUsuarios() {
		List<Usuario> usuarios = userService.getAll();
		if (usuarios.isEmpty()) {
			logger.debug("Usuario does not exists");
			return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + usuarios.size() + " Usuarios");
		logger.debug(Arrays.toString(usuarios.toArray()));
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUsuario(@PathVariable("id") Long id) {
		Usuario usuario = userService.getById(id);
		if (usuario == null) {
			logger.debug("Usuario id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			userService.delete(id);
			logger.debug("Usuario with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}


}
