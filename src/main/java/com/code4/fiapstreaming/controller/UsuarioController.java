package com.code4.fiapstreaming.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validator;
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

import com.code4.fiapstreaming.controller.dto.UsuarioDto;
import com.code4.fiapstreaming.model.Usuario;
import com.code4.fiapstreaming.model.VideoCategoria;
import com.code4.fiapstreaming.service.UsuarioService;
import com.code4.fiapstreaming.service.VideoCategoriaService;

import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    Validator validator;

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

    private <T> Map<Path, String> validar(T dto) {
        Set<ConstraintViolation<T>> violacoes = validator.validate(dto);
        Map<Path, String> violacoesMap = violacoes.stream()
                .collect(Collectors.toMap(violacao -> violacao.getPropertyPath(), violacao -> violacao.getMessage()));
        return violacoesMap;
    }

}