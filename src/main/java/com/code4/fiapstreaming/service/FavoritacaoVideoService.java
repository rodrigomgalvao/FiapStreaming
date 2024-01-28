package com.code4.fiapstreaming.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code4.fiapstreaming.model.FavoritacaoVideo;
import com.code4.fiapstreaming.repository.FavoritacaoVideoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FavoritacaoVideoService {

    @Autowired
    FavoritacaoVideoRepository favoritacaoVideoRepository;

    public Flux<FavoritacaoVideo> findAll() {
        return favoritacaoVideoRepository.findAll();
    }



    public Mono<FavoritacaoVideo> findById(int id){
        return favoritacaoVideoRepository.findById(id);
    }

    public Mono<FavoritacaoVideo> save(FavoritacaoVideo favoritacaoVideo) {
        return favoritacaoVideoRepository.save(favoritacaoVideo);
    }

    public Mono<FavoritacaoVideo> update(int id, FavoritacaoVideo favoritacaoVideo) {
        return favoritacaoVideoRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalFavoritacaoVideoCategoria -> {
                    if (optionalFavoritacaoVideoCategoria.isPresent()) {
                        favoritacaoVideo.setId(id);
                        return favoritacaoVideoRepository.save(favoritacaoVideo);
                    }

                    return Mono.empty();
                });
    }

    public Mono<Void> deleteById(int id) {
        return favoritacaoVideoRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return favoritacaoVideoRepository.deleteAll();
    }

    public Mono<FavoritacaoVideo> marcarComoFavorito(int idUsuario, int idVideo) {
    	FavoritacaoVideo favorito = new FavoritacaoVideo();
        favorito.setIdUsuario(idUsuario);
        favorito.setIdVideo(idVideo);
        return favoritacaoVideoRepository.save(favorito);
    }

    public Mono<Void> desmarcarComoFavorito(int idUsuario, int idVideo) {
        return favoritacaoVideoRepository.deleteByUsuarioIdAndVideoId(idUsuario, idVideo);
    }

}