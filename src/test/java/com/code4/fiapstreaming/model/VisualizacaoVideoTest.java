package com.code4.fiapstreaming.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.UUID;

public class VisualizacaoVideoTest {

    private VisualizacaoVideo visualizacaoVideo;

    @BeforeEach
    void setUp() {
        visualizacaoVideo = new VisualizacaoVideo();
    }

    @Test
    void noArgsConstructor() {
        // Arrange
        // Nada a ser feito, o objeto já foi instanciado no setUp()

        // Act
        // Nada a ser feito, o objeto já foi instanciado no setUp()

        // Assert
        assertNotNull(visualizacaoVideo);
    }

    @Test
    void allArgsConstructor() {
        // Arrange
        UUID id = UUID.randomUUID();
        UUID idUsuario = UUID.randomUUID();
        UUID idVideo = UUID.randomUUID();
        LocalDate dataVisualizacao = LocalDate.now();

        // Act
        VisualizacaoVideo visutalizacaoVideo = new VisualizacaoVideo(id, idUsuario, idVideo, dataVisualizacao);

        // Assert
        assertEquals(id, visutalizacaoVideo.getId());
        assertEquals(idUsuario, visutalizacaoVideo.getIdUsuario());
        assertEquals(idVideo, visutalizacaoVideo.getIdVideo());
        assertEquals(dataVisualizacao, visutalizacaoVideo.getDataVisualizacao());
    }

    @Test
    void getId() {
        // Arrange
        UUID id = UUID.randomUUID();
        visualizacaoVideo.setId(id);

        // Act
        UUID retrievedId = visualizacaoVideo.getId();

        // Assert
        assertEquals(id, retrievedId);
    }

    @Test
    void setId() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        visualizacaoVideo.setId(id);

        // Assert
        assertEquals(id, visualizacaoVideo.getId());
    }

    @Test
    void getIdUsuario() {
        // Arrange
        UUID idUsuario = UUID.randomUUID();
        visualizacaoVideo.setIdUsuario(idUsuario);

        // Act
        UUID retrievedIdUsuario = visualizacaoVideo.getIdUsuario();

        // Assert
        assertEquals(idUsuario, retrievedIdUsuario);
    }

    @Test
    void setIdUsuario() {
        // Arrange
        UUID idUsuario = UUID.randomUUID();

        // Act
        visualizacaoVideo.setIdUsuario(idUsuario);

        // Assert
        assertEquals(idUsuario, visualizacaoVideo.getIdUsuario());
    }

    @Test
    void getIdVideo() {
        // Arrange
        UUID idVideo = UUID.randomUUID();
        visualizacaoVideo.setIdVideo(idVideo);

        // Act
        UUID retrievedIdVideo = visualizacaoVideo.getIdVideo();

        // Assert
        assertEquals(idVideo, retrievedIdVideo);
    }

    @Test
    void setIdVideo() {
        // Arrange
        UUID idVideo = UUID.randomUUID();

        // Act
        visualizacaoVideo.setIdVideo(idVideo);

        // Assert
        assertEquals(idVideo, visualizacaoVideo.getIdVideo());
    }

    @Test
    void getDataFavoritacao() {
        // Arrange
        LocalDate dataVisualizacao = LocalDate.now();
        visualizacaoVideo.setDataVisualizacao(dataVisualizacao);

        // Act
        LocalDate retrievedDataVisualizacao = visualizacaoVideo.getDataVisualizacao();

        // Assert
        assertEquals(dataVisualizacao, retrievedDataVisualizacao);
    }

    @Test
    void setDataFavoritacao() {
        // Arrange
        LocalDate dataVisualizacao = LocalDate.of(2022, 1, 1);

        // Act
        visualizacaoVideo.setDataVisualizacao(dataVisualizacao);

        // Assert
        assertEquals(dataVisualizacao, visualizacaoVideo.getDataVisualizacao());
    }
}