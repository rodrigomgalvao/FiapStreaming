package com.code4.fiapstreaming.service;

import com.code4.fiapstreaming.model.Video;
import com.code4.fiapstreaming.repository.VideoRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.data.domain.Pageable;

public class VideoServiceTest {

    @Mock
    private VideoRepository videoRepository;

    @InjectMocks
    private VideoService videoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllVideos() {
        // Arrange
        Video video1 =
                new Video(UUID.randomUUID(), "Titulo1", "Descricao1", "URL1",
                        LocalDate.now(), UUID.randomUUID());
        Video video2 =
                new Video(UUID.randomUUID(), "Titulo2", "Descricao2", "URL2",
                        LocalDate.now(), UUID.randomUUID());
        List<Video> videos = Arrays.asList(video1, video2);

        when(videoRepository.findAllByOrderByDataPublicacaoDesc(any(Pageable.class)))
                .thenReturn(Flux.fromIterable(videos));

        // Act & Assert
        StepVerifier.create(videoService.findAll(Pageable.unpaged()))
                .expectNext(videos.get(0), videos.get(1))
                .verifyComplete();
    }

    @Test
    void findVideoById() {
        // Arrange
        var id = UUID.randomUUID();
        Video video =
                new Video(id, "Titulo", "Descricao", "URL",
                        LocalDate.now(), UUID.randomUUID());

        when(videoRepository.findById(id)).thenReturn(Mono.just(video));

        // Act & Assert
        StepVerifier.create(videoService.findById(id))
                .expectNext(video)
                .verifyComplete();
    }

    @Test
    void saveVideo() {
        // Arrange
        Video video =
                new Video(UUID.randomUUID(), "Titulo", "Descricao", "URL",
                        LocalDate.now(), UUID.randomUUID());

        when(videoRepository.save(video)).thenReturn(Mono.just(video));

        // Act & Assert
        StepVerifier.create(videoService.save(video))
                .expectNext(video)
                .verifyComplete();
    }

    @Test
    void updateVideo() {
        // Arrange
        var id = UUID.randomUUID();
        Video video =
                new Video(id, "Titulo", "Descricao", "URL",
                        LocalDate.now(), UUID.randomUUID());

        when(videoRepository.save(any())).thenReturn(Mono.just(video));
        when(videoRepository.findById(id)).thenReturn(Mono.just(video));

        // Act & Assert
        StepVerifier.create(videoService.update(id, video))
                .expectNext(video)
                .verifyComplete();
    }

    @Test
    void deleteVideoById() {
        // Arrange
        var id = UUID.randomUUID();
        Video video =
                new Video(id, "Titulo", "Descricao", "URL",
                        LocalDate.now(), UUID.randomUUID());

        when(videoRepository.deleteById(id)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(videoService.deleteById(id))
                .verifyComplete();

        // Verify
        verify(videoRepository).deleteById(id);
    }
}