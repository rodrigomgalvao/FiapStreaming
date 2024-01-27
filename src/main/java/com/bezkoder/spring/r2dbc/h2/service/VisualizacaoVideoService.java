package com.bezkoder.spring.r2dbc.h2.service;

import com.bezkoder.spring.r2dbc.h2.model.VisualizacaoVideo;
import com.bezkoder.spring.r2dbc.h2.repository.VisualizacaoVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class VisualizacaoVideoService {
    @Autowired
    VisualizacaoVideoRepository visualizacaoVideoRepository;
    public Flux<VisualizacaoVideo> findAll() {
        return visualizacaoVideoRepository.findAll();
    }
    public Mono<VisualizacaoVideo> findById(int id){
        return visualizacaoVideoRepository.findById(id);
    }
    public Mono<VisualizacaoVideo> save(VisualizacaoVideo visualizacaoVideo) {
        return visualizacaoVideoRepository.save(visualizacaoVideo);
    }
    public Mono<VisualizacaoVideo> update(int id, VisualizacaoVideo visualizacaoVideo) {
        return visualizacaoVideoRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalVideoCategoria -> {
                    if (optionalVideoCategoria.isPresent()) {
                        visualizacaoVideo.setId(id);
                        return visualizacaoVideoRepository.save(visualizacaoVideo);
                    }

                    return Mono.empty();
                });
    }
    public Mono<Void> deleteById(int id) {
        return visualizacaoVideoRepository.deleteById(id);
    }
    public Mono<Void> deleteAll() {
        return visualizacaoVideoRepository.deleteAll();
    }
}
