package com.code4.fiapstreaming.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
    }

    @Test
    void getId() {
        // Arrange
        UUID id = UUID.randomUUID();
        usuario.setId(id);

        // Act

        // Assert
        assertEquals(id, usuario.getId());
    }

    @Test
    void setId() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        usuario.setId(id);

        // Assert
        assertEquals(id, usuario.getId());
    }

    @Test
    void getNomeUsuario() {
        // Arrange
        String nomeUsuario = "Joao";
        usuario.setNomeUsuario(nomeUsuario);

        // Act

        // Assert
        assertEquals(nomeUsuario, usuario.getNomeUsuario());
    }

    @Test
    void setNomeUsuario() {
        // Arrange
        String nomeUsuario = "Maria";

        // Act
        usuario.setNomeUsuario(nomeUsuario);

        // Assert
        assertEquals(nomeUsuario, usuario.getNomeUsuario());
    }

    @Test
    void testNoArgsConstructor() {
        // Arrange

        // Act
        Usuario usuario = new Usuario();

        // Assert
        assertNotNull(usuario);
    }

    @Test
    void testAllArgsConstructor() {
        // Arrange
        UUID id = UUID.randomUUID();
        String nomeUsuario = "Fulano";

        // Act
        Usuario usuario = new Usuario(id, nomeUsuario);

        // Assert
        assertEquals(id, usuario.getId());
        assertEquals(nomeUsuario, usuario.getNomeUsuario());
    }
}