package com.code4.fiapstreaming.service;

import com.code4.fiapstreaming.model.Usuario;
import com.code4.fiapstreaming.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import static org.mockito.Mockito.when;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllUsuarios() {
        // Arrange
        Usuario usuario1 = new Usuario(UUID.randomUUID(), "Joao");
        Usuario usuario2 = new Usuario(UUID.randomUUID(), "Maria");
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

        when(usuarioRepository.findAll()).thenReturn(Flux.fromIterable(usuarios));

        // Act & Assert
        StepVerifier.create(usuarioService.findAll())
                .expectNext(usuarios.get(0), usuarios.get(1))
                .verifyComplete();
    }

    @Test
    void findUsuarioById() {
        // Arrange
        var id = UUID.randomUUID();
        Usuario usuario = new Usuario(id, "Joao");

        when(usuarioRepository.findById(id)).thenReturn(Mono.just(usuario));

        // Act & Assert
        StepVerifier.create(usuarioService.findById(id))
                .expectNext(usuario)
                .verifyComplete();
    }

    @Test
    void saveUsuario() {
        // Arrange
        Usuario usuario = new Usuario(UUID.randomUUID(), "Joao");

        when(usuarioRepository.save(usuario)).thenReturn(Mono.just(usuario));

        // Act & Assert
        StepVerifier.create(usuarioService.save(usuario))
                .expectNext(usuario)
                .verifyComplete();
    }

    @Test
    void updateUsuario() {
        // Arrange
        var id = UUID.randomUUID();
        Usuario usuario = new Usuario(id, "Joao");

        when(usuarioRepository.save(usuario)).thenReturn(Mono.just(usuario));
        when(usuarioRepository.findById(id)).thenReturn(Mono.just(usuario));

        // Act & Assert
        StepVerifier.create(usuarioService.update(id, usuario))
                .expectNext(usuario)
                .verifyComplete();
    }

    @Test
    void deleteUsuarioById() {
        // Arrange
        var id = UUID.randomUUID();
        Usuario usuario = new Usuario(id, "Joao");

        when(usuarioRepository.deleteById(id)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(usuarioService.deleteById(id))
                .verifyComplete();
    }
}

