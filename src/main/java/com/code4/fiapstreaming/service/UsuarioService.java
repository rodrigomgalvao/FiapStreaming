package com.code4.fiapstreaming.service;

import java.util.Optional;
import java.util.UUID;

import com.code4.fiapstreaming.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code4.fiapstreaming.model.Usuario;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuarioService {

  @Autowired
  UsuarioRepository usuarioRepository;

  public Flux<Usuario> findAll() {
    return usuarioRepository.findAll();
  }

  public Mono<Usuario> findById(UUID id){
    return usuarioRepository.findById(id);
  }

  public Mono<Usuario> save(Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

  public Mono<Usuario> update(UUID id, Usuario usuario) {
    return usuarioRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
        .flatMap(optionalVideoCategoria -> {
          if (optionalVideoCategoria.isPresent()) {
        	  usuario.setId(id);
            return usuarioRepository.save(usuario);
          }

          return Mono.empty();
        });
  }
  public Mono<Void> deleteById(UUID id) {
    return usuarioRepository.deleteById(id);
  }

}
