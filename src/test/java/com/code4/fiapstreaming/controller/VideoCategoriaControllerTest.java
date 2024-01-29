package com.code4.fiapstreaming.controller;

import com.code4.fiapstreaming.model.VideoCategoria;
import com.code4.fiapstreaming.service.VideoCategoriaService;
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

public class VideoCategoriaControllerTest {

    @Mock
    private VideoCategoriaService videoCategoriaService;

    @InjectMocks
    private VideoCategoriaController videoCategoriaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategorias() {
        // Arrange
        VideoCategoria videoCategoria1 = new VideoCategoria(UUID.randomUUID(), "Ação");
        VideoCategoria videoCategoria2 = new VideoCategoria(UUID.randomUUID(), "Comédia");
        List<VideoCategoria> videoCategorias = Arrays.asList(videoCategoria1, videoCategoria2);

        when(videoCategoriaService.findAll()).thenReturn(Flux.fromIterable(videoCategorias));

        // Act
        ResponseEntity<Flux<VideoCategoria>> response = videoCategoriaController.getAllVideoCategorias();

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(videoCategoria1, videoCategoria2)
                .verifyComplete();
    }

    @Test
    void getCategoriaById() {
        // Arrange
        var id = UUID.randomUUID();
        VideoCategoria videoCategoria = new VideoCategoria(id, "Ação");

        when(videoCategoriaService.findById(id)).thenReturn(Mono.just(videoCategoria));

        // Act
        ResponseEntity<Mono<VideoCategoria>> response = videoCategoriaController.getVideoCategoriaById(id);

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(videoCategoria)
                .verifyComplete();
    }

    @Test
    void createCategoria() {
        // Arrange
        VideoCategoria videoCategoria = new VideoCategoria(UUID.randomUUID(), "Ação");

        when(videoCategoriaService.save(videoCategoria)).thenReturn(Mono.just(videoCategoria));

        // Act
        ResponseEntity<Mono<VideoCategoria>> response = videoCategoriaController.createVideoCategoria(videoCategoria);

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(videoCategoria)
                .verifyComplete();
    }

    @Test
    void updateCategoria() {
        // Arrange
        var id = UUID.randomUUID();
        VideoCategoria videoCategoria = new VideoCategoria(id, "Ação");

        when(videoCategoriaService.update(id, videoCategoria)).thenReturn(Mono.just(videoCategoria));

        // Act
        ResponseEntity<Mono<VideoCategoria>> response = videoCategoriaController.updateVideoCategoria(id, videoCategoria);

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(videoCategoria)
                .verifyComplete();
    }

    @Test
    void deleteCategoria() {
        // Arrange
        var id = UUID.randomUUID();

        when(videoCategoriaService.deleteById(id)).thenReturn(Mono.empty());

        // Act
        ResponseEntity<Void> response = videoCategoriaController.deleteVideoCategoria(id);

        // Assert
        verify(videoCategoriaService).deleteById(id);
        HttpStatusCode statusCode = response.getStatusCode();
        assert statusCode.equals(HttpStatus.NO_CONTENT);
    }
}
