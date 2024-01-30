package com.code4.fiapstreaming.controller;

import com.code4.fiapstreaming.model.VisualizacaoVideo;
import com.code4.fiapstreaming.service.VisualizacaoVideoService;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VisualizacaoVideoControllerTest {

    @Mock
    private VisualizacaoVideoService visualizacaoVideoService;

    @InjectMocks
    private VisualizacaoVideoController visualizacaoVideoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllVisualizacoes() {
        // Arrange
        VisualizacaoVideo visualizacao1 =
                new VisualizacaoVideo(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        VisualizacaoVideo visualizacao2 =
                new VisualizacaoVideo(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        List<VisualizacaoVideo> visualizacoes = Arrays.asList(visualizacao1, visualizacao2);

        when(visualizacaoVideoService.findAll()).thenReturn(Flux.fromIterable(visualizacoes));

        // Act
        ResponseEntity<Flux<VisualizacaoVideo>> response = visualizacaoVideoController.getAllVisualizacaoVideos();

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(visualizacao1, visualizacao2)
                .verifyComplete();
    }

    @Test
    void getVisualizacaoById() {
        // Arrange
        var id = UUID.randomUUID();
        VisualizacaoVideo visualizacao =
                new VisualizacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());

        when(visualizacaoVideoService.findById(id)).thenReturn(Mono.just(visualizacao));

        // Act
        ResponseEntity<Mono<VisualizacaoVideo>> response = visualizacaoVideoController.getVisualizacaoVideoById(id);

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(visualizacao)
                .verifyComplete();
    }

    @Test
    void createVisualizacao() {
        // Arrange
        VisualizacaoVideo visualizacao =
                new VisualizacaoVideo(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());

        when(visualizacaoVideoService.save(visualizacao)).thenReturn(Mono.just(visualizacao));

        // Act
        ResponseEntity<Mono<VisualizacaoVideo>> response =
                visualizacaoVideoController.createVisualizacaoVideo1(visualizacao);

        // Assert
        StepVerifier.create(response.getBody())
                .assertNext(body -> {
                    assertThat(body).isEqualTo(visualizacao);
                })
                .expectComplete()
                .verify();
    }


    @Test
    void updateVisualizacao() {
        // Arrange
        var id = UUID.randomUUID();
        VisualizacaoVideo visualizacao =
                new VisualizacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());

        when(visualizacaoVideoService.update(id, visualizacao)).thenReturn(Mono.just(visualizacao));

        // Act
        ResponseEntity<Mono<VisualizacaoVideo>> response =
                visualizacaoVideoController.updateVisualizacaoVideo(id, visualizacao);

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(visualizacao)
                .verifyComplete();
    }

    @Test
    void deleteVisualizacao() {
        // Arrange
        var id = UUID.randomUUID();

        when(visualizacaoVideoService.deleteById(id)).thenReturn(Mono.empty());

        // Act
        ResponseEntity<Void> response = visualizacaoVideoController.deleteVisualizacaoVideo(id);

        // Assert
        verify(visualizacaoVideoService).deleteById(id);
        HttpStatusCode statusCode = response.getStatusCode();
        assert statusCode.equals(HttpStatus.NO_CONTENT);
    }
}
