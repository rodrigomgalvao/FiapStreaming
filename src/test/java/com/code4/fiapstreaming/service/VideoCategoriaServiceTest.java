package com.code4.fiapstreaming.service;

import com.code4.fiapstreaming.model.VideoCategoria;
import com.code4.fiapstreaming.repository.VideoCategoriaRepository;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VideoCategoriaServiceTest {

    @Mock
    private VideoCategoriaRepository videoCategoriaRepository;

    @InjectMocks
    private VideoCategoriaService videoCategoriaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllVideoCategorias() {
        // Arrange
        VideoCategoria categoria1 = new VideoCategoria(UUID.randomUUID(), "Categoria1");
        VideoCategoria categoria2 = new VideoCategoria(UUID.randomUUID(), "Categoria2");
        List<VideoCategoria> categorias = Arrays.asList(categoria1, categoria2);

        when(videoCategoriaRepository.findAll()).thenReturn(Flux.fromIterable(categorias));

        // Act & Assert
        StepVerifier.create(videoCategoriaService.findAll())
                .expectNext(categoria1, categoria2)
                .verifyComplete();
    }

    @Test
    void findVideoCategoriaById() {
        // Arrange
        var id = UUID.randomUUID();
        VideoCategoria categoria = new VideoCategoria(id, "Categoria");

        when(videoCategoriaRepository.findById(id)).thenReturn(Mono.just(categoria));

        // Act & Assert
        StepVerifier.create(videoCategoriaService.findById(id))
                .expectNext(categoria)
                .verifyComplete();
    }

    @Test
    void saveVideoCategoria() {
        // Arrange
        VideoCategoria categoria = new VideoCategoria(UUID.randomUUID(), "Categoria");

        when(videoCategoriaRepository.save(categoria)).thenReturn(Mono.just(categoria));

        // Act & Assert
        StepVerifier.create(videoCategoriaService.save(categoria))
                .expectNext(categoria)
                .verifyComplete();
    }

    @Test
    void updateVideoCategoria() {
        // Arrange
        var id = UUID.randomUUID();
        VideoCategoria categoria = new VideoCategoria(id, "Categoria");

        when(videoCategoriaRepository.save(any())).thenReturn(Mono.just(categoria));
        when(videoCategoriaRepository.findById(id)).thenReturn(Mono.just(categoria));

        // Act & Assert
        StepVerifier.create(videoCategoriaService.update(id, categoria))
                .expectNext(categoria)
                .verifyComplete();
    }

    @Test
    void deleteVideoCategoriaById() {
        // Arrange
        var id = UUID.randomUUID();
        VideoCategoria categoria = new VideoCategoria(id, "Categoria");

        when(videoCategoriaRepository.deleteById(id)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(videoCategoriaService.deleteById(id))
                .verifyComplete();

        // Verify
        verify(videoCategoriaRepository).deleteById(id);
    }
}
