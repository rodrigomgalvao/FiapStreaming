package com.code4.fiapstreaming.repository;


import com.code4.fiapstreaming.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UsuarioRepositoryTest {
    @Mock
    private UsuarioRepository usuarioRepository;
    AutoCloseable openMocks;
    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }
    @Test
    void devePermitirSalvarUsuario() {
        // Arrange
        var id = UUID.randomUUID();
        Usuario usuario = new Usuario(id, "Joao");
        when(usuarioRepository.save(any())).thenReturn(Mono.just(usuario));

        // Act & Assert
        usuarioRepository.save(usuario)
                .as(StepVerifier::create)
                .expectNext(usuario)
                .verifyComplete();
    }
    @Test
    public void deveEncontrarUsuarioPorId() {
        // Arrange
        var id = UUID.randomUUID();
        Usuario usuario = new Usuario(id, "Joao");
        when(usuarioRepository.findById(id)).thenReturn(Mono.just(usuario));

        // Act & Assert
        usuarioRepository.findById(id)
                .as(StepVerifier::create)
                .expectNext(usuario)
                .verifyComplete();
    }

    @Test
    public void devePermitirAtualizarUsuario() {
        // Arrange
        var id = UUID.randomUUID();
        Usuario usuario = new Usuario(id, "Joao");
        when(usuarioRepository.save(any())).thenReturn(Mono.just(usuario));
        when(usuarioRepository.findById(id)).thenReturn(Mono.just(usuario));

        // Act & Assert
        usuarioRepository.save(usuario)
                .then(usuarioRepository.findById(id))
                .map(user -> {
                    user.setNomeUsuario("Joao da Silva");
                    return user;
                })
                .flatMap(usuarioRepository::save)
                .as(StepVerifier::create)
                .expectNextMatches(updatedUser -> updatedUser.getNomeUsuario().equals("Joao da Silva"))
                .verifyComplete();
    }

    @Test
    public void devePermitirExcluirUsuario() {
        // Arrange
        var id = UUID.randomUUID();
        Usuario usuario = new Usuario(id, "Joao");
        when(usuarioRepository.save(any())).thenReturn(Mono.just(usuario));
        when(usuarioRepository.findById(id)).thenReturn(Mono.just(usuario));
        when(usuarioRepository.deleteById(id)).thenReturn(Mono.empty());

        // Act & Assert
        usuarioRepository.save(usuario)
                .then(usuarioRepository.deleteById(id))
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }
    @Test
    void deveRetornarTodosUsuarios() {
        // Arrange
        Usuario usuario1 = new Usuario(UUID.randomUUID(), "Joao");
        Usuario usuario2 = new Usuario(UUID.randomUUID(), "Maria");
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

        when(usuarioRepository.findAll()).thenReturn(Flux.fromIterable(usuarios));

        // Act & Assert
        usuarioRepository.findAll()
                .as(StepVerifier::create)
                .expectNext(usuarios.toArray(new Usuario[0]))
                .verifyComplete();
    }
}