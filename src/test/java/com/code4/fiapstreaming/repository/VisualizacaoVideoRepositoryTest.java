package com.code4.fiapstreaming.repository;

import com.code4.fiapstreaming.model.VisualizacaoVideo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class VisualizacaoVideoRepositoryTest {

    @Mock
    private VisualizacaoVideoRepository visualizacaoVideoRepository;

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
    void devePermitirSalvarVisualizacaoVideo() {
        // Arrange
        var id = UUID.randomUUID();
        VisualizacaoVideo visualizacaoVideo =
                new VisualizacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        when(visualizacaoVideoRepository.save(any())).thenReturn(Mono.just(visualizacaoVideo));

        // Act & Assert
        visualizacaoVideoRepository.save(visualizacaoVideo)
                .as(StepVerifier::create)
                .expectNext(visualizacaoVideo)
                .verifyComplete();
    }

    @Test
    public void deveEncontrarVisualizacaoVideoPorId() {
        // Arrange
        var id = UUID.randomUUID();
        VisualizacaoVideo visualizacaoVideo = new VisualizacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        when(visualizacaoVideoRepository.findById(id)).thenReturn(Mono.just(visualizacaoVideo));

        // Act & Assert
        visualizacaoVideoRepository.findById(id)
                .as(StepVerifier::create)
                .expectNext(visualizacaoVideo)
                .verifyComplete();
    }

    @Test
    public void devePermitirAtualizarVisualizacaoVideo() {
        // Arrange
        var id = UUID.randomUUID();
        VisualizacaoVideo visualizacaoVideo =
                new VisualizacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        when(visualizacaoVideoRepository.save(any())).thenReturn(Mono.just(visualizacaoVideo));
        when(visualizacaoVideoRepository.findById(id)).thenReturn(Mono.just(visualizacaoVideo));

        // Act & Assert
        visualizacaoVideoRepository.save(visualizacaoVideo)
                .then(visualizacaoVideoRepository.findById(id))
                .map(vv -> {
                    vv.setDataVisualizacao(LocalDate.now().minusDays(1));
                    return vv;
                })
                .flatMap(visualizacaoVideoRepository::save)
                .as(StepVerifier::create)
                .expectNextMatches(updatedVisualizacao ->
                        updatedVisualizacao.getDataVisualizacao().
                                isEqual(LocalDate.now().minusDays(1)))
                .verifyComplete();
    }

    @Test
    public void devePermitirExcluirVisualizacaoVideo() {
        // Arrange
        var id = UUID.randomUUID();
        VisualizacaoVideo visualizacaoVideo =
                new VisualizacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        when(visualizacaoVideoRepository.save(any())).thenReturn(Mono.just(visualizacaoVideo));
        when(visualizacaoVideoRepository.findById(id)).thenReturn(Mono.just(visualizacaoVideo));
        when(visualizacaoVideoRepository.deleteById(id)).thenReturn(Mono.empty());

        // Act & Assert
        visualizacaoVideoRepository.save(visualizacaoVideo)
                .then(visualizacaoVideoRepository.deleteById(id))
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }

    @Test
    void deveRetornarTodasVisualizacaoVideos() {
        // Arrange
        VisualizacaoVideo visualizacaoVideo1 =
                new VisualizacaoVideo(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        VisualizacaoVideo visualizacaoVideo2 =
                new VisualizacaoVideo(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        List<VisualizacaoVideo> visualizacaoVideos = Arrays.asList(visualizacaoVideo1, visualizacaoVideo2);

        when(visualizacaoVideoRepository.findAll()).thenReturn(Flux.fromIterable(visualizacaoVideos));

        // Act & Assert
        visualizacaoVideoRepository.findAll()
                .as(StepVerifier::create)
                .expectNext(visualizacaoVideos.toArray(new VisualizacaoVideo[0]))
                .verifyComplete();
    }
}
