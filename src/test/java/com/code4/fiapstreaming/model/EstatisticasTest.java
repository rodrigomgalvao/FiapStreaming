package com.code4.fiapstreaming.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstatisticasTest {

    private Estatisticas estatisticas;

    @BeforeEach
    void setUp() {
        estatisticas = new Estatisticas();
    }

    @Test
    void testNoArgsConstructor() {
        assertNotNull(estatisticas);
    }

    @Test
    void testAllArgsConstructor() {
        // Arrange
        long totalVideos = 10;
        long totalFavoritos = 5;
        double mediaVisualizacoes = 20.5;

        // Act
        Estatisticas estatisticas = new Estatisticas(totalVideos, totalFavoritos, mediaVisualizacoes);

        // Assert
        assertNotNull(estatisticas);
        assertEquals(totalVideos, estatisticas.getTotalVideos());
        assertEquals(totalFavoritos, estatisticas.getTotalFavoritos());
        assertEquals(mediaVisualizacoes, estatisticas.getMediaVisualizacoes());
    }

    @Test
    void testGetSetTotalVideos() {
        // Arrange
        long totalVideos = 20;

        // Act
        estatisticas.setTotalVideos(totalVideos);

        // Assert
        assertEquals(totalVideos, estatisticas.getTotalVideos());
    }

    @Test
    void testGetSetTotalFavoritos() {
        // Arrange
        long totalFavoritos = 15;

        // Act
        estatisticas.setTotalFavoritos(totalFavoritos);

        // Assert
        assertEquals(totalFavoritos, estatisticas.getTotalFavoritos());
    }

    @Test
    void testGetSetMediaVisualizacoes() {
        // Arrange
        double mediaVisualizacoes = 25.5;

        // Act
        estatisticas.setMediaVisualizacoes(mediaVisualizacoes);

        // Assert
        assertEquals(mediaVisualizacoes, estatisticas.getMediaVisualizacoes());
    }
}
