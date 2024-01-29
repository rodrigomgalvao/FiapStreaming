package com.code4.fiapstreaming.controller;

import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code4.fiapstreaming.model.Usuario;
import com.code4.fiapstreaming.service.UsuarioService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    Validator validator;
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Mono<Usuario>> getUsuarioById(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }
    @GetMapping("/usuarios")
    public ResponseEntity<Flux<Usuario>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.findAll());
    }
    @PostMapping("/usuarios")
    public ResponseEntity<Mono<Usuario>> createUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Mono<Usuario>> updateUsuario(@PathVariable UUID id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.update(id, usuario));
    }
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable UUID id) {
        usuarioService.deleteById(id).subscribe();
        return ResponseEntity.noContent().build();
    }

}
