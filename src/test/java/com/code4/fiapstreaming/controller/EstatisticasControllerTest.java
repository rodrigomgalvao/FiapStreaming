package com.code4.fiapstreaming.controller;

import com.code4.fiapstreaming.model.Estatisticas;
import com.code4.fiapstreaming.service.FavoritacaoVideoService;
import com.code4.fiapstreaming.service.VideoService;
import com.code4.fiapstreaming.service.VisualizacaoVideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

public class EstatisticasControllerTest {

    @Mock
    private VideoService videoService;

    @Mock
    private FavoritacaoVideoService favoritacaoVideoService;

    @Mock
    private VisualizacaoVideoService visualizacaoVideoService;

    @InjectMocks
    private EstatisticasController estatisticasController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obterEstatisticas() {
        // Arrange
        long totalVideos = 10;
        long totalFavoritos = 5;
        double mediaVisualizacoes = 15.5;

        when(videoService.countVideos()).thenReturn(Mono.just(totalVideos));
        when(favoritacaoVideoService.countFavoritos()).thenReturn(Mono.just(totalFavoritos));
        when(visualizacaoVideoService.calculateMediaVisualizacoes()).thenReturn(Mono.just(mediaVisualizacoes));

        // Act
        Mono<Estatisticas> estatisticasMono = estatisticasController.obterEstatisticas();

        // Assert
        StepVerifier.create(estatisticasMono)
                .expectNextMatches(estatisticas ->
                        estatisticas.getTotalVideos() == totalVideos &&
                                estatisticas.getTotalFavoritos() == totalFavoritos &&
                                estatisticas.getMediaVisualizacoes() == mediaVisualizacoes)
                .verifyComplete();
    }
}
