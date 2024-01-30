package com.code4.fiapstreaming.controller;

import com.code4.fiapstreaming.model.Video;
import com.code4.fiapstreaming.service.RecomendacaoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.Mockito.when;

public class RecomendacaoControllerTest {

    @Mock
    private RecomendacaoService recomendacaoService;

    @InjectMocks
    private RecomendacaoController recomendacaoController;

    public RecomendacaoControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obterRecomendacoes() {
        // Arrange
        UUID idUsuario = UUID.randomUUID();
        Video video1 = new Video(UUID.randomUUID(), "Video 1", "Descrição 1", "url1", null, UUID.randomUUID());
        Video video2 = new Video(UUID.randomUUID(), "Video 2", "Descrição 2", "url2", null, UUID.randomUUID());
        Flux<Video> videosFlux = Flux.just(video1, video2);

        when(recomendacaoService.recomendarVideosComBaseNosFavoritos(idUsuario)).thenReturn(videosFlux);

        // Act
        Flux<Video> recomendacoes = recomendacaoController.obterRecomendacoes(idUsuario);

        // Assert
        StepVerifier.create(recomendacoes)
                .expectNext(video1)
                .expectNext(video2)
                .verifyComplete();
    }
}
