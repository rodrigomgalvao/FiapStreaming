package com.code4.fiapstreaming.controllerTest;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.code4.fiapstreaming.controller.FavoritacaoVideoController;
import com.code4.fiapstreaming.service.FavoritacaoVideoService;

import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class FavoritacaoVideoControllerTest {

    @Mock
    FavoritacaoVideoService favoritacaoVideoService;

    @InjectMocks
    FavoritacaoVideoController favoritacaoVideoController;

    @Test
    void testDesmarcarComoFavorito_Success() {
        // Dados de entrada
        Map<String, Integer> requestBody = new HashMap<>();
        requestBody.put("idUsuario", 1);
        requestBody.put("idVideo", 2);

        // Configuração do serviço mock
        when(favoritacaoVideoService.desmarcarComoFavorito(1, 2)).thenReturn(Mono.empty());

        // Chamada do método e verificação
        Mono<String> result = favoritacaoVideoController.desmarcarComoFavorito(requestBody);
        assertDoesNotThrow(() -> {
            String message = result.block();
            assertEquals("Operação de desmarcar favorito realizada com sucesso.", message);
        });
    }

    @Test
    void testDesmarcarComoFavorito_Error() {
        // Dados de entrada inválidos (null)
        Map<String, Integer> requestBody = new HashMap<>();
        requestBody.put("idUsuario", null);
        requestBody.put("idVideo", 2);

        // Chamada do método e verificação
        Mono<String> result = favoritacaoVideoController.desmarcarComoFavorito(requestBody);
        assertThrows(IllegalArgumentException.class, () -> result.block());
    }
}
