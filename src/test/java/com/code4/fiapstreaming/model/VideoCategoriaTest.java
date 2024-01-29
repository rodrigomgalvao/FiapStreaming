package com.code4.fiapstreaming.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

public class VideoCategoriaTest {

    private VideoCategoria videoCategoria;

    @BeforeEach
    void setUp() {
        videoCategoria = new VideoCategoria();
    }

    @Test
    void noArgsConstructor() {
        // Arrange

        // Act

        // Assert
        assertNotNull(videoCategoria);
    }

    @Test
    void allArgsConstructor() {
        // Arrange
        UUID id = UUID.randomUUID();
        String titulo = "Categoria";

        // Act
        VideoCategoria videoCategoria = new VideoCategoria(id, titulo);

        // Assert
        assertEquals(id, videoCategoria.getId());
        assertEquals(titulo, videoCategoria.getTitulo());
    }

    @Test
    void getId() {
        // Arrange
        UUID id = UUID.randomUUID();
        videoCategoria.setId(id);

        // Act

        // Assert
        assertEquals(id, videoCategoria.getId());
    }

    @Test
    void setId() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        videoCategoria.setId(id);

        // Assert
        assertEquals(id, videoCategoria.getId());
    }

    @Test
    void getTitulo() {
        // Arrange
        String titulo = "Categoria";
        videoCategoria.setTitulo(titulo);

        // Act

        // Assert
        assertEquals(titulo, videoCategoria.getTitulo());
    }

    @Test
    void setTitulo() {
        // Arrange
        String titulo = "Nova Categoria";

        // Act
        videoCategoria.setTitulo(titulo);

        // Assert
        assertEquals(titulo, videoCategoria.getTitulo());
    }
}
