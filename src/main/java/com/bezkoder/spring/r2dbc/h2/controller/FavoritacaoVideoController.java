package com.bezkoder.spring.r2dbc.h2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.r2dbc.h2.model.FavoritacaoVideo;
import com.bezkoder.spring.r2dbc.h2.service.FavoritacaoVideoService;

import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
public class FavoritacaoVideoController {
    @Autowired
    FavoritacaoVideoService favoritacaoVideoService;


    @GetMapping("/favoritacaoVideo/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<FavoritacaoVideo> getFavoritacaovideoById(@PathVariable("id") int id) {
        return favoritacaoVideoService.findById(id);

    }

    @PostMapping("/favoritacaoVideo")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FavoritacaoVideo> createVideo(@RequestBody FavoritacaoVideo favoritacaoVideo) {
        return favoritacaoVideoService.save(favoritacaoVideo);
    }


    @PutMapping("/FavoritacaoVideo/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<FavoritacaoVideo> updateVideo(@PathVariable("id") int id, @RequestBody FavoritacaoVideo favoritacaoVideo) {
        return favoritacaoVideoService.update(id, favoritacaoVideo);
    }

    @DeleteMapping("/favoritacaoVideo/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteFavoritacaovideo(@PathVariable("id") int id) {
        return favoritacaoVideoService.deleteById(id);
    }

    @DeleteMapping("/favoritacaoVideo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAllFavoritacaovideos() {
        return favoritacaoVideoService.deleteAll();
    }


}