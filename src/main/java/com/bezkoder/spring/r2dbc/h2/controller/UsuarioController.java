package com.bezkoder.spring.r2dbc.h2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bezkoder.spring.r2dbc.h2.model.Usuario;
import com.bezkoder.spring.r2dbc.h2.model.VideoCategoria;
import com.bezkoder.spring.r2dbc.h2.service.UsuarioService;
import com.bezkoder.spring.r2dbc.h2.service.VideoCategoriaService;

import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
public class UsuarioController {
  @Autowired
  UsuarioService usuarioService;
  


  
  @GetMapping("/usuario/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Usuario> getUsuarioById(@PathVariable("id") int id) {
	   return usuarioService.findById(id);

  }

  @PostMapping("/usuario")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Usuario> createUsuario(@RequestBody Usuario usuario) {
    return usuarioService.save(usuario);
  }
  


  @PutMapping("/usuario/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Usuario> updateUsuario(@PathVariable("id") int id, @RequestBody Usuario usuario) {
	  return usuarioService.update(id, usuario);
  }

  @DeleteMapping("/usuario/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteVideoCategoria(@PathVariable("id") int id) {
    return usuarioService.deleteById(id);
  }

  @DeleteMapping("/usuario")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteAllUsuarios() {
    return usuarioService.deleteAll();
  }


}
