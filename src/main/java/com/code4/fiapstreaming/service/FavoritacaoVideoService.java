package com.code4.fiapstreaming.service;

import java.util.Optional;
import java.util.UUID;

import com.code4.fiapstreaming.repository.FavoritacaoVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code4.fiapstreaming.model.FavoritacaoVideo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FavoritacaoVideoService {

    @Autowired
    FavoritacaoVideoRepository favoritacaoVideoRepository;

    public Flux<FavoritacaoVideo> findAll() {
        return favoritacaoVideoRepository.findAll();
    }

    public Mono<FavoritacaoVideo> findById(UUID id){
        return favoritacaoVideoRepository.findById(id);
    }

    public Mono<FavoritacaoVideo> save(FavoritacaoVideo favoritacaoVideo) {
        return favoritacaoVideoRepository.save(favoritacaoVideo);
    }

    public Mono<FavoritacaoVideo> update(UUID id, FavoritacaoVideo favoritacaoVideo) {
        return favoritacaoVideoRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalFavoritacaoVideoCategoria -> {
                    if (optionalFavoritacaoVideoCategoria.isPresent()) {
                        favoritacaoVideo.setId(id);
                        return favoritacaoVideoRepository.save(favoritacaoVideo);
                    }

                    return Mono.empty();
                });
    }

    public Mono<Void> deleteById(UUID id) {
        return favoritacaoVideoRepository.deleteById(id);
    }

    public Mono<FavoritacaoVideo> marcarComoFavorito(UUID idUsuario, UUID idVideo) {
    	FavoritacaoVideo favorito = new FavoritacaoVideo();
        favorito.setIdUsuario(idUsuario);
        favorito.setIdVideo(idVideo);
        return favoritacaoVideoRepository.save(favorito);
    }

    public Mono<Void> desmarcarComoFavorito(UUID idUsuario, UUID idVideo) {
        return favoritacaoVideoRepository.deleteByUsuarioIdAndVideoId(idUsuario, idVideo);
    }

}