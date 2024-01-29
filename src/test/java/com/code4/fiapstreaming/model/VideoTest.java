package com.code4.fiapstreaming.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.UUID;

public class VideoTest {

    private Video video;

    @BeforeEach
    void setUp() {
        video = new Video();
    }

    @Test
    void noArgsConstructor() {
        // Arrange

        // Act

        // Assert
        assertNotNull(video);
    }

    @Test
    void allArgsConstructor() {
        // Arrange
        UUID id = UUID.randomUUID();
        String tituloVideo = "Titulo do Video";
        String descricao = "Descricao do Video";
        String url = "https://example.com";
        LocalDate dataPublicacao = LocalDate.now();
        UUID idCategoria = UUID.randomUUID();

        // Act
        Video video = new Video(id, tituloVideo, descricao, url, dataPublicacao, idCategoria);

        // Assert
        assertEquals(id, video.getId());
        assertEquals(tituloVideo, video.getTituloVideo());
        assertEquals(descricao, video.getDescricao());
        assertEquals(url, video.getUrl());
        assertEquals(dataPublicacao, video.getDataPublicacao());
        assertEquals(idCategoria, video.getIdCategoria());
    }

    @Test
    void getId() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        video.setId(id);

        // Assert
        assertEquals(id, video.getId());
    }

    @Test
    void setId() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        video.setId(id);

        // Assert
        assertEquals(id, video.getId());
    }

    @Test
    void getTituloVideo() {
        // Arrange
        String tituloVideo = "Titulo do Video";

        // Act
        video.setTituloVideo(tituloVideo);

        // Assert
        assertEquals(tituloVideo, video.getTituloVideo());
    }

    @Test
    void setTituloVideo() {
        // Arrange
        String tituloVideo = "Titulo do Video";

        // Act
        video.setTituloVideo(tituloVideo);

        // Assert
        assertEquals(tituloVideo, video.getTituloVideo());
    }

    @Test
    void getDescricao() {
        // Arrange
        String descricao = "Descricao do Video";

        // Act
        video.setDescricao(descricao);

        // Assert
        assertEquals(descricao, video.getDescricao());
    }

    @Test
    void setDescricao() {
        // Arrange
        String descricao = "Descricao do Video";

        // Act
        video.setDescricao(descricao);

        // Assert
        assertEquals(descricao, video.getDescricao());
    }

    @Test
    void getUrl() {
        // Arrange
        String url = "https://example.com";

        // Act
        video.setUrl(url);

        // Assert
        assertEquals(url, video.getUrl());
    }

    @Test
    void setUrl() {
        // Arrange
        String url = "https://example.com";

        // Act
        video.setUrl(url);

        // Assert
        assertEquals(url, video.getUrl());
    }

    @Test
    void getDataPublicacao() {
        // Arrange
        LocalDate dataPublicacao = LocalDate.now();

        // Act
        video.setDataPublicacao(dataPublicacao);

        // Assert
        assertEquals(dataPublicacao, video.getDataPublicacao());
    }

    @Test
    void setDataPublicacao() {
        // Arrange
        LocalDate dataPublicacao = LocalDate.now();

        // Act
        video.setDataPublicacao(dataPublicacao);

        // Assert
        assertEquals(dataPublicacao, video.getDataPublicacao());
    }

    @Test
    void getIdCategoria() {
        // Arrange
        UUID idCategoria = UUID.randomUUID();

        // Act
        video.setIdCategoria(idCategoria);

        // Assert
        assertEquals(idCategoria, video.getIdCategoria());
    }

    @Test
    void setIdCategoria() {
        // Arrange
        UUID idCategoria = UUID.randomUUID();

        // Act
        video.setIdCategoria(idCategoria);

        // Assert
        assertEquals(idCategoria, video.getIdCategoria());
    }
}