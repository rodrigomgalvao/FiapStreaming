package com.code4.fiapstreaming.controllerTest;



import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.code4.fiapstreaming.controller.FavoritacaoVideoController;
import com.code4.fiapstreaming.service.FavoritacaoVideoService;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class FavoritacaoVideoControllerTest {

    @Mock
    FavoritacaoVideoService favoritacaoVideoService;

    @InjectMocks
    FavoritacaoVideoController favoritacaoVideoController;

    @Test
    void testDesmarcarComoFavorito_Success() {
        // Dados de entrada
        Map<String, UUID> requestBody = new HashMap<>();
        requestBody.put("idUsuario", UUID.randomUUID());
        requestBody.put("idVideo", UUID.randomUUID());

        // Configuração do serviço mock
        when(favoritacaoVideoService.desmarcarComoFavorito(any(UUID.class), any(UUID.class))).thenReturn(Mono.empty());

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
        Map<String, UUID> requestBody = new HashMap<>();
        requestBody.put("idUsuario", null);
        requestBody.put("idVideo", UUID.randomUUID());

        // Chamada do método e verificação
        Mono<String> result = favoritacaoVideoController.desmarcarComoFavorito(requestBody);
        assertThrows(IllegalArgumentException.class, () -> result.block());
    }

}
