package com.code4.fiapstreaming.controller;

import com.code4.fiapstreaming.model.Usuario;
import com.code4.fiapstreaming.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsuarios() {
        // Arrange
        Usuario usuario1 = new Usuario(UUID.randomUUID(), "usuario1");
        Usuario usuario2 = new Usuario(UUID.randomUUID(), "usuario2");
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

        when(usuarioService.findAll()).thenReturn(Flux.fromIterable(usuarios));

        // Act
        ResponseEntity<Flux<Usuario>> response = usuarioController.getAllUsuarios();

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(usuario1, usuario2)
                .verifyComplete();
    }

    @Test
    void getUsuarioById() {
        // Arrange
        var id = UUID.randomUUID();
        Usuario usuario = new Usuario(id, "user1");

        when(usuarioService.findById(id)).thenReturn(Mono.just(usuario));

        // Act
        ResponseEntity<Mono<Usuario>> response = usuarioController.getUsuarioById(id);

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(usuario)
                .verifyComplete();
    }

    @Test
    void createUsuario() {
        // Arrange
        Usuario usuario = new Usuario(UUID.randomUUID(), "user1");

        when(usuarioService.save(usuario)).thenReturn(Mono.just(usuario));

        // Act
        ResponseEntity<Mono<Usuario>> response = usuarioController.createUsuario(usuario);

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(usuario)
                .verifyComplete();
    }

    @Test
    void updateUsuario() {
        // Arrange
        var id = UUID.randomUUID();
        Usuario usuario = new Usuario(id, "user1");

        when(usuarioService.update(id, usuario)).thenReturn(Mono.just(usuario));

        // Act
        ResponseEntity<Mono<Usuario>> response = usuarioController.updateUsuario(id, usuario);

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(usuario)
                .verifyComplete();
    }

    @Test
    void deleteUsuario() {
        // Arrange
        var id = UUID.randomUUID();

        when(usuarioService.deleteById(id)).thenReturn(Mono.empty());

        // Act
        ResponseEntity<Void> response = usuarioController.deleteUsuario(id);

        // Assert
        verify(usuarioService).deleteById(id);
        HttpStatusCode statusCode = response.getStatusCode();
        assert statusCode.equals(HttpStatus.NO_CONTENT);
    }
}
