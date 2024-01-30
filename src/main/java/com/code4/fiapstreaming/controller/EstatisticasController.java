package com.code4.fiapstreaming.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code4.fiapstreaming.model.Estatisticas;
import com.code4.fiapstreaming.service.FavoritacaoVideoService;
import com.code4.fiapstreaming.service.VideoService;
import com.code4.fiapstreaming.service.VisualizacaoVideoService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class EstatisticasController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private FavoritacaoVideoService favoritacaoVideoService;

    @Autowired
    private VisualizacaoVideoService visualizacaoVideoService;

    @GetMapping("/estatistica")
    public Mono<Estatisticas> obterEstatisticas() {
        Mono<Long> totalVideosMono = videoService.countVideos();
        Mono<Long> totalFavoritosMono = favoritacaoVideoService.countFavoritos();
        Mono<Double> mediaVisualizacoesMono = visualizacaoVideoService.calculateMediaVisualizacoes();

        return Mono.zip(totalVideosMono, totalFavoritosMono, mediaVisualizacoesMono)
                .flatMap(tuple -> {
                    long totalVideos = tuple.getT1();
                    long totalFavoritos = tuple.getT2();
                    double mediaVisualizacoes = tuple.getT3();

                    Estatisticas estatisticas = new Estatisticas(totalVideos, totalFavoritos, mediaVisualizacoes);
                    return Mono.just(estatisticas);
                });
    }

}
