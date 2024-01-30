package com.code4.fiapstreaming.service;

import com.code4.fiapstreaming.model.FavoritacaoVideo;
import com.code4.fiapstreaming.repository.FavoritacaoVideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavoritacaoVideoServiceTest {

    @Mock
    private FavoritacaoVideoRepository favoritacaoVideoRepository;

    @InjectMocks
    private FavoritacaoVideoService favoritacaoVideoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllFavoritacoesVideos() {
        // Arrange
        FavoritacaoVideo favoritacaoVideo1 =
                new FavoritacaoVideo(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        FavoritacaoVideo favoritacaoVideo2 =
                new FavoritacaoVideo(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        List<FavoritacaoVideo> favoritacaoVideos = Arrays.asList(favoritacaoVideo1, favoritacaoVideo2);

        when(favoritacaoVideoRepository.findAll()).thenReturn(Flux.fromIterable(favoritacaoVideos));

        // Act & Assert
        StepVerifier.create(favoritacaoVideoService.findAll())
                .expectNext(favoritacaoVideo1, favoritacaoVideo2)
                .verifyComplete();
    }

    @Test
    void findFavoritacaoVideoById() {
        // Arrange
        var id = UUID.randomUUID();
        FavoritacaoVideo favoritacaoVideo =
                new FavoritacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());

        when(favoritacaoVideoRepository.findById(id)).thenReturn(Mono.just(favoritacaoVideo));

        // Act & Assert
        StepVerifier.create(favoritacaoVideoService.findById(id))
                .expectNext(favoritacaoVideo)
                .verifyComplete();
    }

    @Test
    void saveFavoritacaoVideo() {
        // Arrange
        FavoritacaoVideo favoritacaoVideo =
                new FavoritacaoVideo(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());

        when(favoritacaoVideoRepository.save(favoritacaoVideo)).thenReturn(Mono.just(favoritacaoVideo));

        // Act & Assert
        StepVerifier.create(favoritacaoVideoService.save(favoritacaoVideo))
                .expectNext(favoritacaoVideo)
                .verifyComplete();
    }

    @Test
    void updateFavoritacaoVideo() {
        // Arrange
        var id = UUID.randomUUID();
        FavoritacaoVideo favoritacaoVideo =
                new FavoritacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());

        when(favoritacaoVideoRepository.save(any())).thenReturn(Mono.just(favoritacaoVideo));
        when(favoritacaoVideoRepository.findById(id)).thenReturn(Mono.just(favoritacaoVideo));

        // Act & Assert
        StepVerifier.create(favoritacaoVideoService.update(id, favoritacaoVideo))
                .expectNext(favoritacaoVideo)
                .verifyComplete();
    }
    @Test
    void deleteFavoritacaoVideoById() {
        // Arrange
        var id = UUID.randomUUID();
        FavoritacaoVideo favoritacaoVideo =
                new FavoritacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());

        when(favoritacaoVideoRepository.deleteById(id)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(favoritacaoVideoService.deleteById(id))
                .verifyComplete();

        // Verify
        verify(favoritacaoVideoRepository).deleteById(id);
    }
    @Test
    void desmarcarComoFavorito() {
        // Arrange
        UUID idUsuario = UUID.randomUUID();
        UUID idVideo = UUID.randomUUID();
        when(favoritacaoVideoRepository.deleteByUsuarioIdAndVideoId(idUsuario, idVideo)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(favoritacaoVideoService.desmarcarComoFavorito(idUsuario, idVideo))
                .verifyComplete();
    }

    @Test
    void countFavoritos() {
        // Arrange
        long count = 5;
        when(favoritacaoVideoRepository.count()).thenReturn(Mono.just(count));

        // Act & Assert
        StepVerifier.create(favoritacaoVideoService.countFavoritos())
                .expectNext(count)
                .verifyComplete();
    }
}
