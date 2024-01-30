package com.code4.fiapstreaming.controller;

import com.code4.fiapstreaming.model.Video;
import com.code4.fiapstreaming.service.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VideoControllerTest {

    @Mock
    private VideoService videoService;

    @InjectMocks
    private VideoController videoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllVideos() {
        // Arrange
        Video video1 = new Video(UUID.randomUUID(), "Título1", "Descrição1", "URL1",
                LocalDate.now(), UUID.randomUUID());
        Video video2 = new Video(UUID.randomUUID(), "Título2", "Descrição2", "URL2",
                LocalDate.now(), UUID.randomUUID());
        List<Video> videos = Arrays.asList(video1, video2);

        when(videoService.findAll(any(Pageable.class))).thenReturn(Flux.fromIterable(videos));

        // Act
        ResponseEntity<Flux<Video>> response = videoController.getAllVideos(0, 3);

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(video1, video2)
                .verifyComplete();
    }

    @Test
    void getVideoById() {
        // Arrange
        var id = UUID.randomUUID();
        Video video = new Video(id, "Título", "Descrição", "URL",
                LocalDate.now(), UUID.randomUUID());

        when(videoService.findById(id)).thenReturn(Mono.just(video));

        // Act
        ResponseEntity<Mono<Video>> response = videoController.getVideoById(id);

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(video)
                .verifyComplete();
    }

    @Test
    void createVideo() {
        // Arrange
        Video video = new Video(UUID.randomUUID(), "Título", "Descrição", "URL", LocalDate.now(), UUID.randomUUID());

        when(videoService.save(video)).thenReturn(Mono.just(video));

        // Act
        ResponseEntity<Mono<Video>> response = videoController.createVideo(video);

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(video)
                .verifyComplete();
    }

    @Test
    void updateVideo() {
        // Arrange
        var id = UUID.randomUUID();
        Video video = new Video(id, "Título", "Descrição", "URL", LocalDate.now(), UUID.randomUUID());

        when(videoService.update(id, video)).thenReturn(Mono.just(video));

        // Act
        ResponseEntity<Mono<Video>> response = videoController.updateVideo(id, video);

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(video)
                .verifyComplete();
    }

    @Test
    void deleteVideo() {
        // Arrange
        var id = UUID.randomUUID();

        when(videoService.deleteById(id)).thenReturn(Mono.empty());
        // Act
        ResponseEntity<Void> response = videoController.deleteVideo(id);

        // Assert
        verify(videoService).deleteById(id);
        HttpStatusCode statusCode = response.getStatusCode();
        assert statusCode.equals(HttpStatus.NO_CONTENT);
    }

    @Test
    void getVideos_NullCriteria_ReturnsErrorMessage() {
        // Arrange
        String expectedMessage = "Por favor, forneça critérios de busca (titulo e/ou dataPublicacao).";

        // Act
        Flux<Object> response = videoController.getVideos(null, null);

        // Assert
        StepVerifier.create(response)
                .expectNext(expectedMessage)
                .verifyComplete();
    }

}
