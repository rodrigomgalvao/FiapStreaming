package com.code4.fiapstreaming.controller;

import com.code4.fiapstreaming.model.FavoritacaoVideo;
import com.code4.fiapstreaming.service.FavoritacaoVideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavoritacaoVideoControllerTest {

    @Mock
    private FavoritacaoVideoService favoritacaoVideoService;

    @InjectMocks
    private FavoritacaoVideoController favoritacaoVideoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllFavoritacoes() {
        // Arrange
        FavoritacaoVideo favoritacao1 =
                new FavoritacaoVideo(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        FavoritacaoVideo favoritacao2 =
                new FavoritacaoVideo(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        List<FavoritacaoVideo> favoritacoes = Arrays.asList(favoritacao1, favoritacao2);

        when(favoritacaoVideoService.findAll()).thenReturn(Flux.fromIterable(favoritacoes));

        // Act
        ResponseEntity<Flux<FavoritacaoVideo>> response = favoritacaoVideoController.getAllFavoritacoes();

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(favoritacao1, favoritacao2)
                .verifyComplete();
    }

    @Test
    void getFavoritacaoById() {
        // Arrange
        var id = UUID.randomUUID();
        FavoritacaoVideo favoritacao = new FavoritacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());

        when(favoritacaoVideoService.findById(id)).thenReturn(Mono.just(favoritacao));

        // Act
        ResponseEntity<Mono<FavoritacaoVideo>> response = favoritacaoVideoController.getFavoritacaoById(id);

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(favoritacao)
                .verifyComplete();
    }

    @Test
    void createFavoritacao() {
        // Arrange
        FavoritacaoVideo favoritacao =
                new FavoritacaoVideo(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());

        when(favoritacaoVideoService.save(favoritacao)).thenReturn(Mono.just(favoritacao));

        // Act
        ResponseEntity<Mono<FavoritacaoVideo>> response = favoritacaoVideoController.createFavoritacao(favoritacao);

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(favoritacao)
                .verifyComplete();
    }

    @Test
    void updateFavoritacao() {
        // Arrange
        var id = UUID.randomUUID();
        FavoritacaoVideo favoritacao =
                new FavoritacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());

        when(favoritacaoVideoService.update(id, favoritacao)).thenReturn(Mono.just(favoritacao));

        // Act
        ResponseEntity<Mono<FavoritacaoVideo>> response = favoritacaoVideoController.updateFavoritacao(id, favoritacao);

        // Assert
        StepVerifier.create(response.getBody())
                .expectNext(favoritacao)
                .verifyComplete();
    }
    @Test
    void deleteFavoritacao() {
        // Arrange
        var id = UUID.randomUUID();

        when(favoritacaoVideoService.deleteById(id)).thenReturn(Mono.empty());
        // Act
        ResponseEntity<Void> response = favoritacaoVideoController.deleteFavoritacao(id);

        // Assert
        verify(favoritacaoVideoService).deleteById(id);
        HttpStatusCode statusCode = response.getStatusCode();
        assert statusCode.equals(HttpStatus.NO_CONTENT);
    }

    @Test
    void marcarComoFavorito() {
        // Arrange
        UUID idUsuario = UUID.randomUUID();
        UUID idVideo = UUID.randomUUID();
        FavoritacaoVideo favoritacaoVideo = new FavoritacaoVideo(idUsuario, idVideo);
        Map<String, UUID> requestBody = new HashMap<>();
        requestBody.put("idUsuario", idUsuario);
        requestBody.put("idVideo", idVideo);
        when(favoritacaoVideoService.marcarComoFavorito(idUsuario, idVideo)).thenReturn(Mono.just(favoritacaoVideo));

        // Act
        Mono<FavoritacaoVideo> response = favoritacaoVideoController.marcarComoFavorito(requestBody);

        // Assert
        StepVerifier.create(response)
                .expectNext(favoritacaoVideo)
                .verifyComplete();
    }

    @Test
    void desmarcarComoFavorito() {
        // Arrange
        UUID idUsuario = UUID.randomUUID();
        UUID idVideo = UUID.randomUUID();
        Map<String, UUID> requestBody = new HashMap<>();
        requestBody.put("idUsuario", idUsuario);
        requestBody.put("idVideo", idVideo);
        when(favoritacaoVideoService.desmarcarComoFavorito(idUsuario, idVideo)).thenReturn(Mono.empty());

        // Act
        Mono<String> response = favoritacaoVideoController.desmarcarComoFavorito(requestBody);

        // Assert
        StepVerifier.create(response)
                .expectNext("Operação de desmarcar favorito realizada com sucesso.")
                .verifyComplete();
    }

    @Test
    void getVideoCategoriaById() {
        // Arrange
        FavoritacaoVideo favoritacaoVideo1 = new FavoritacaoVideo(UUID.randomUUID(), UUID.randomUUID());
        FavoritacaoVideo favoritacaoVideo2 = new FavoritacaoVideo(UUID.randomUUID(), UUID.randomUUID());
        Flux<FavoritacaoVideo> favoritacoes = Flux.just(favoritacaoVideo1, favoritacaoVideo2);
        when(favoritacaoVideoService.findAll()).thenReturn(favoritacoes);

        // Act
        Flux<FavoritacaoVideo> response = favoritacaoVideoController.getVideoCategoriaById();

        // Assert
        StepVerifier.create(response)
                .expectNext(favoritacaoVideo1, favoritacaoVideo2)
                .verifyComplete();
    }
}
