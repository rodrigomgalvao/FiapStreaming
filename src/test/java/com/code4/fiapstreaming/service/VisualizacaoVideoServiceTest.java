package com.code4.fiapstreaming.service;

import com.code4.fiapstreaming.model.VisualizacaoVideo;
import com.code4.fiapstreaming.repository.VisualizacaoVideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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
import static org.mockito.Mockito.verify;

public class VisualizacaoVideoServiceTest {

    @Mock
    private VisualizacaoVideoRepository visualizacaoVideoRepository;

    @InjectMocks
    private VisualizacaoVideoService visualizacaoVideoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllVisualizacoesVideos() {
        // Arrange
        VisualizacaoVideo visualizacaoVideo1 =
                new VisualizacaoVideo(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        VisualizacaoVideo visualizacaoVideo2 =
                new VisualizacaoVideo(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        List<VisualizacaoVideo> visualizacaoVideos = Arrays.asList(visualizacaoVideo1, visualizacaoVideo2);

        when(visualizacaoVideoRepository.findAll()).thenReturn(Flux.fromIterable(visualizacaoVideos));

        // Act & Assert
        StepVerifier.create(visualizacaoVideoService.findAll())
                .expectNext(visualizacaoVideo1, visualizacaoVideo2)
                .verifyComplete();
    }

    @Test
    void findVisualizacaoVideoById() {
        // Arrange
        var id = UUID.randomUUID();
        VisualizacaoVideo visualizacaoVideo =
                new VisualizacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());

        when(visualizacaoVideoRepository.findById(id)).thenReturn(Mono.just(visualizacaoVideo));

        // Act & Assert
        StepVerifier.create(visualizacaoVideoService.findById(id))
                .expectNext(visualizacaoVideo)
                .verifyComplete();
    }

    @Test
    void saveVisualizacaoVideo() {
        // Arrange
        VisualizacaoVideo visualizacaoVideo =
                new VisualizacaoVideo(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());

        when(visualizacaoVideoRepository.save(visualizacaoVideo)).thenReturn(Mono.just(visualizacaoVideo));

        // Act & Assert
        StepVerifier.create(visualizacaoVideoService.save(visualizacaoVideo))
                .expectNext(visualizacaoVideo)
                .verifyComplete();
    }

    @Test
    void updateVisualizacaoVideo() {
        // Arrange
        var id = UUID.randomUUID();
        VisualizacaoVideo visualizacaoVideo =
                new VisualizacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());

        when(visualizacaoVideoRepository.save(any())).thenReturn(Mono.just(visualizacaoVideo));
        when(visualizacaoVideoRepository.findById(id)).thenReturn(Mono.just(visualizacaoVideo));

        // Act & Assert
        StepVerifier.create(visualizacaoVideoService.update(id, visualizacaoVideo))
                .expectNext(visualizacaoVideo)
                .verifyComplete();
    }

    @Test
    void deleteVisualizacaoVideoById() {
        // Arrange
        var id = UUID.randomUUID();
        VisualizacaoVideo visualizacaoVideo =
                new VisualizacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());

        when(visualizacaoVideoRepository.deleteById(id)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(visualizacaoVideoService.deleteById(id))
                .verifyComplete();

        // Verify
        verify(visualizacaoVideoRepository).deleteById(id);
    }

}