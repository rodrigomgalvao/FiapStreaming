package com.code4.fiapstreaming.repository;

import com.code4.fiapstreaming.model.FavoritacaoVideo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import static org.mockito.Mockito.when;

public class FavoritacaoVideoRepositoryTest {

    @Mock
    private FavoritacaoVideoRepository favoritacaoVideoRepository;

    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirSalvarFavoritacaoVideo() {
        // Arrange
        var id = UUID.randomUUID();
        FavoritacaoVideo favoritacaoVideo =
                new FavoritacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        when(favoritacaoVideoRepository.save(any())).thenReturn(Mono.just(favoritacaoVideo));

        // Act & Assert
        favoritacaoVideoRepository.save(favoritacaoVideo)
                .as(StepVerifier::create)
                .expectNext(favoritacaoVideo)
                .verifyComplete();
    }

    @Test
    public void deveEncontrarFavoritacaoVideoPorId() {
        // Arrange
        var id = UUID.randomUUID();
        FavoritacaoVideo favoritacaoVideo =
                new FavoritacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        when(favoritacaoVideoRepository.findById(id)).thenReturn(Mono.just(favoritacaoVideo));

        // Act & Assert
        favoritacaoVideoRepository.findById(id)
                .as(StepVerifier::create)
                .expectNext(favoritacaoVideo)
                .verifyComplete();
    }

    @Test
    public void devePermitirAtualizarFavoritacaoVideo() {
        // Arrange
        var id = UUID.randomUUID();
        FavoritacaoVideo favoritacaoVideo =
                new FavoritacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        when(favoritacaoVideoRepository.save(any())).thenReturn(Mono.just(favoritacaoVideo));
        when(favoritacaoVideoRepository.findById(id)).thenReturn(Mono.just(favoritacaoVideo));

        // Act & Assert
        favoritacaoVideoRepository.save(favoritacaoVideo)
                .then(favoritacaoVideoRepository.findById(id))
                .map(fv -> {
                    fv.setDataFavoritacao(LocalDate.now().minusDays(1)); // Update dataFavoritacao
                    return fv;
                })
                .flatMap(favoritacaoVideoRepository::save)
                .as(StepVerifier::create)
                .expectNextMatches(updatedFavoritacao ->
                        updatedFavoritacao.getDataFavoritacao().isEqual(LocalDate.now().minusDays(1)))
                .verifyComplete();
    }

    @Test
    public void devePermitirExcluirFavoritacaoVideo() {
        // Arrange
        var id = UUID.randomUUID();
        FavoritacaoVideo favoritacaoVideo =
                new FavoritacaoVideo(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        when(favoritacaoVideoRepository.save(any())).thenReturn(Mono.just(favoritacaoVideo));
        when(favoritacaoVideoRepository.findById(id)).thenReturn(Mono.just(favoritacaoVideo));
        when(favoritacaoVideoRepository.deleteById(id)).thenReturn(Mono.empty());

        // Act & Assert
        favoritacaoVideoRepository.save(favoritacaoVideo)
                .then(favoritacaoVideoRepository.deleteById(id))
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }

    @Test
    void deveRetornarTodasFavoritacaoVideos() {
        // Arrange
        FavoritacaoVideo favoritacaoVideo1 =
                new FavoritacaoVideo(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        FavoritacaoVideo favoritacaoVideo2 =
                new FavoritacaoVideo(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDate.now());
        List<FavoritacaoVideo> favoritacaoVideos = Arrays.asList(favoritacaoVideo1, favoritacaoVideo2);

        when(favoritacaoVideoRepository.findAll()).thenReturn(Flux.fromIterable(favoritacaoVideos));

        // Act & Assert
        favoritacaoVideoRepository.findAll()
                .as(StepVerifier::create)
                .expectNext(favoritacaoVideos.toArray(new FavoritacaoVideo[0]))
                .verifyComplete();
    }
}

