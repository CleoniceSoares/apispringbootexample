package com.example.apiexamplespringboot.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.apiexamplespringboot.model.Usuario;
import com.example.apiexamplespringboot.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository _usuarioRepository;

    public List<Usuario> findAll() {
        return _usuarioRepository.findAll();
    }

    public ResponseEntity<Usuario> findById(UUID id) {
        Optional<Usuario> usuario = _usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Usuario save(Usuario usuario) {
        return _usuarioRepository.save(usuario);
    }

    public ResponseEntity<Usuario> update(UUID id, Usuario newUsuario) {
        Optional<Usuario> oldUsuario = _usuarioRepository.findById(id);

        if (oldUsuario.isPresent()) {
            Usuario usuario = oldUsuario.get();

            usuario.setLogin(newUsuario.getLogin());
            usuario.setSenha(newUsuario.getSenha());

            _usuarioRepository.save(usuario);

            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> delete(UUID id) {
        Optional<Usuario> usuario = _usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            _usuarioRepository.delete(usuario.get());

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
