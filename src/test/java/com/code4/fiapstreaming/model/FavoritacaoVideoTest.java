package com.code4.fiapstreaming.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.UUID;

public class FavoritacaoVideoTest {

    private FavoritacaoVideo favoritacaoVideo;

    @BeforeEach
    void setUp() {
        favoritacaoVideo = new FavoritacaoVideo();
    }

    @Test
    void noArgsConstructor() {
        // Arrange
        // Nada a ser feito, o objeto já foi instanciado no setUp()

        // Act
        // Nada a ser feito, o objeto já foi instanciado no setUp()

        // Assert
        assertNotNull(favoritacaoVideo);
    }

    @Test
    void allArgsConstructor() {
        // Arrange
        UUID id = UUID.randomUUID();
        UUID idUsuario = UUID.randomUUID();
        UUID idVideo = UUID.randomUUID();
        LocalDate dataFavoritacao = LocalDate.now();

        // Act
        FavoritacaoVideo favoritacaoVideo = new FavoritacaoVideo(id, idUsuario, idVideo, dataFavoritacao);

        // Assert
        assertEquals(id, favoritacaoVideo.getId());
        assertEquals(idUsuario, favoritacaoVideo.getIdUsuario());
        assertEquals(idVideo, favoritacaoVideo.getIdVideo());
        assertEquals(dataFavoritacao, favoritacaoVideo.getDataFavoritacao());
    }

    @Test
    void getId() {
        // Arrange
        UUID id = UUID.randomUUID();
        favoritacaoVideo.setId(id);

        // Act
        UUID retrievedId = favoritacaoVideo.getId();

        // Assert
        assertEquals(id, retrievedId);
    }

    @Test
    void setId() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        favoritacaoVideo.setId(id);

        // Assert
        assertEquals(id, favoritacaoVideo.getId());
    }

    @Test
    void getIdUsuario() {
        // Arrange
        UUID idUsuario = UUID.randomUUID();
        favoritacaoVideo.setIdUsuario(idUsuario);

        // Act
        UUID retrievedIdUsuario = favoritacaoVideo.getIdUsuario();

        // Assert
        assertEquals(idUsuario, retrievedIdUsuario);
    }

    @Test
    void setIdUsuario() {
        // Arrange
        UUID idUsuario = UUID.randomUUID();

        // Act
        favoritacaoVideo.setIdUsuario(idUsuario);

        // Assert
        assertEquals(idUsuario, favoritacaoVideo.getIdUsuario());
    }

    @Test
    void getIdVideo() {
        // Arrange
        UUID idVideo = UUID.randomUUID();
        favoritacaoVideo.setIdVideo(idVideo);

        // Act
        UUID retrievedIdVideo = favoritacaoVideo.getIdVideo();

        // Assert
        assertEquals(idVideo, retrievedIdVideo);
    }

    @Test
    void setIdVideo() {
        // Arrange
        UUID idVideo = UUID.randomUUID();

        // Act
        favoritacaoVideo.setIdVideo(idVideo);

        // Assert
        assertEquals(idVideo, favoritacaoVideo.getIdVideo());
    }

    @Test
    void getDataFavoritacao() {
        // Arrange
        LocalDate dataFavoritacao = LocalDate.now();
        favoritacaoVideo.setDataFavoritacao(dataFavoritacao);

        // Act
        LocalDate retrievedDataFavoritacao = favoritacaoVideo.getDataFavoritacao();

        // Assert
        assertEquals(dataFavoritacao, retrievedDataFavoritacao);
    }

    @Test
    void setDataFavoritacao() {
        // Arrange
        LocalDate dataFavoritacao = LocalDate.of(2022, 1, 1);

        // Act
        favoritacaoVideo.setDataFavoritacao(dataFavoritacao);

        // Assert
        assertEquals(dataFavoritacao, favoritacaoVideo.getDataFavoritacao());
    }
}