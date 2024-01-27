package com.bezkoder.spring.r2dbc.h2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.r2dbc.h2.model.FavoritacaoVideo;
import com.bezkoder.spring.r2dbc.h2.repository.FavoritacaoVideoRepository;

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


}