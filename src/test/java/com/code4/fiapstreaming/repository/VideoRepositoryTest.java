package com.code4.fiapstreaming.repository;
import com.code4.fiapstreaming.model.Video;
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

public class VideoRepositoryTest {

    @Mock
    private VideoRepository videoRepository;

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
    void devePermitirSalvarVideo() {
        // Arrange
        var id = UUID.randomUUID();
        Video video = new Video(id, "Titulo", "Descrição", "URL", LocalDate.now(), UUID.randomUUID());
        when(videoRepository.save(any())).thenReturn(Mono.just(video));

        // Act & Assert
        videoRepository.save(video)
                .as(StepVerifier::create)
                .expectNext(video)
                .verifyComplete();
    }

    @Test
    public void deveEncontrarVideoPorId() {
        // Arrange
        var id = UUID.randomUUID();
        Video video = new Video(id, "Titulo", "Descrição", "URL", LocalDate.now(), UUID.randomUUID());
        when(videoRepository.findById(id)).thenReturn(Mono.just(video));

        // Act & Assert
        videoRepository.findById(id)
                .as(StepVerifier::create)
                .expectNext(video)
                .verifyComplete();
    }

    @Test
    public void devePermitirAtualizarVideo() {
        // Arrange
        var id = UUID.randomUUID();
        Video video = new Video(id, "Titulo", "Descrição", "URL", LocalDate.now(), UUID.randomUUID());
        when(videoRepository.save(any())).thenReturn(Mono.just(video));
        when(videoRepository.findById(id)).thenReturn(Mono.just(video));

        // Act & Assert
        videoRepository.save(video)
                .then(videoRepository.findById(id))
                .map(v -> {
                    v.setTituloVideo("Novo Título");
                    return v;
                })
                .flatMap(videoRepository::save)
                .as(StepVerifier::create)
                .expectNextMatches(updatedVideo -> updatedVideo.getTituloVideo().equals("Novo Título"))
                .verifyComplete();
    }

    @Test
    public void devePermitirExcluirVideo() {
        // Arrange
        var id = UUID.randomUUID();
        Video video = new Video(id, "Titulo", "Descrição", "URL", LocalDate.now(), UUID.randomUUID());
        when(videoRepository.save(any())).thenReturn(Mono.just(video));
        when(videoRepository.findById(id)).thenReturn(Mono.just(video));
        when(videoRepository.deleteById(id)).thenReturn(Mono.empty());

        // Act & Assert
        videoRepository.save(video)
                .then(videoRepository.deleteById(id))
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }

    @Test
    void deveRetornarTodosVideos() {
        // Arrange
        Video video1 = new Video(UUID.randomUUID(), "Titulo1", "Descrição1", "URL1", LocalDate.now(), UUID.randomUUID());
        Video video2 = new Video(UUID.randomUUID(), "Titulo2", "Descrição2", "URL2", LocalDate.now(), UUID.randomUUID());
        List<Video> videos = Arrays.asList(video1, video2);

        when(videoRepository.findAll()).thenReturn(Flux.fromIterable(videos));

        // Act & Assert
        videoRepository.findAll()
                .as(StepVerifier::create)
                .expectNext(videos.toArray(new Video[0]))
                .verifyComplete();
    }
}

