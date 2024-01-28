package com.code4.fiapstreaming.repository;
import com.code4.fiapstreaming.model.VideoCategoria;
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
import static org.mockito.Mockito.when;

public class VideoCategoriaRepositoryTest {

    @Mock
    private VideoCategoriaRepository videoCategoriaRepository;

    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirSalvarVideoCategoria() {
        // Arrange
        var id = UUID.randomUUID();
        VideoCategoria videoCategoria = new VideoCategoria(id, "Ação");
        when(videoCategoriaRepository.save(any())).thenReturn(Mono.just(videoCategoria));

        // Act & Assert
        videoCategoriaRepository.save(videoCategoria)
                .as(StepVerifier::create)
                .expectNext(videoCategoria)
                .verifyComplete();
    }

    @Test
    public void deveEncontrarVideoCategoriaPorId() {
        // Arrange
        var id = UUID.randomUUID();
        VideoCategoria videoCategoria = new VideoCategoria(id, "Ação");
        when(videoCategoriaRepository.findById(id)).thenReturn(Mono.just(videoCategoria));

        // Act & Assert
        videoCategoriaRepository.findById(id)
                .as(StepVerifier::create)
                .expectNext(videoCategoria)
                .verifyComplete();
    }

    @Test
    public void devePermitirAtualizarVideoCategoria() {
        // Arrange
        var id = UUID.randomUUID();
        VideoCategoria videoCategoria = new VideoCategoria(id, "Ação");
        when(videoCategoriaRepository.save(any())).thenReturn(Mono.just(videoCategoria));
        when(videoCategoriaRepository.findById(id)).thenReturn(Mono.just(videoCategoria));

        // Act & Assert
        videoCategoriaRepository.save(videoCategoria)
                .then(videoCategoriaRepository.findById(id))
                .map(vc -> {
                    vc.setTitulo("Comédia");
                    return vc;
                })
                .flatMap(videoCategoriaRepository::save)
                .as(StepVerifier::create)
                .expectNextMatches(updatedVC -> updatedVC.getTitulo().equals("Comédia"))
                .verifyComplete();
    }

    @Test
    public void devePermitirExcluirVideoCategoria() {
        // Arrange
        var id = UUID.randomUUID();
        VideoCategoria videoCategoria = new VideoCategoria(id, "Ação");
        when(videoCategoriaRepository.save(any())).thenReturn(Mono.just(videoCategoria));
        when(videoCategoriaRepository.findById(id)).thenReturn(Mono.just(videoCategoria));
        when(videoCategoriaRepository.deleteById(id)).thenReturn(Mono.empty());

        // Act & Assert
        videoCategoriaRepository.save(videoCategoria)
                .then(videoCategoriaRepository.deleteById(id))
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }

    @Test
    void deveRetornarTodasVideoCategorias() {
        // Arrange
        VideoCategoria videoCategoria1 = new VideoCategoria(UUID.randomUUID(), "Ação");
        VideoCategoria videoCategoria2 = new VideoCategoria(UUID.randomUUID(), "Comédia");
        List<VideoCategoria> videoCategorias = Arrays.asList(videoCategoria1, videoCategoria2);

        when(videoCategoriaRepository.findAll()).thenReturn(Flux.fromIterable(videoCategorias));

        // Act & Assert
        videoCategoriaRepository.findAll()
                .as(StepVerifier::create)
                .expectNext(videoCategorias.toArray(new VideoCategoria[0]))
                .verifyComplete();
    }
}
