package com.code4.fiapstreaming.service;

import com.code4.fiapstreaming.model.Video;
import com.code4.fiapstreaming.repository.FavoritacaoVideoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.Mockito.when;

public class RecomendacaoServiceTest {

    @Mock
    private FavoritacaoVideoRepository favoritacaoVideoRepository;

    @Mock
    private VideoService videoService;

    @InjectMocks
    private RecomendacaoService recomendacaoService;

    public RecomendacaoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void recomendarVideosComBaseNosFavoritos() {
        // Arrange
        UUID idUsuario = UUID.randomUUID();
        UUID videoId1 = UUID.randomUUID();
        UUID videoId2 = UUID.randomUUID();
        Video video1 = new Video(videoId1, "Video 1", "Descrição 1", "url1", null, UUID.randomUUID());
        Video video2 = new Video(videoId2, "Video 2", "Descrição 2", "url2", null, UUID.randomUUID());
        Flux<UUID> videoIdsFlux = Flux.just(videoId1, videoId2);
        when(favoritacaoVideoRepository.findVideoIdsByUserId(idUsuario)).thenReturn(videoIdsFlux);
        when(videoService.findById(videoId1)).thenReturn(Mono.just(video1));
        when(videoService.findById(videoId2)).thenReturn(Mono.just(video2));

        // Act
        Flux<Video> recomendacoes = recomendacaoService.recomendarVideosComBaseNosFavoritos(idUsuario);

        // Assert
        StepVerifier.create(recomendacoes)
                .expectNext(video1)
                .expectNext(video2)
                .verifyComplete();
    }
}
