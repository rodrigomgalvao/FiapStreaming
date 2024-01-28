package com.code4.fiapstreaming.controller;

import com.code4.fiapstreaming.model.VisualizacaoVideo;
import com.code4.fiapstreaming.service.VisualizacaoVideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class VisualizacaoVideoController {
    @Autowired
    VisualizacaoVideoService visualizacaoVideoService;

    @GetMapping("/visualizacaovideo/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<VisualizacaoVideo> getVisualizacaoVideoById(@PathVariable("id") UUID id) {
        return visualizacaoVideoService.findById(id);

    }

    @PostMapping("/visualizacaovideo")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<VisualizacaoVideo> createVisualizacaoVideo(@RequestBody VisualizacaoVideo visualizacaoVideo) {
        return visualizacaoVideoService.save(visualizacaoVideo);
    }

    @PutMapping("/visualizacaovideo/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<VisualizacaoVideo> updateVisualizacaoVideo(@PathVariable("id") UUID id, @RequestBody VisualizacaoVideo visualizacaoVideo) {
        return visualizacaoVideoService.update(id, visualizacaoVideo);
    }

    @DeleteMapping("/visualizacaovideo/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteVisualizacaoVideo(@PathVariable("id") UUID id) {
        return visualizacaoVideoService.deleteById(id);
    }

    @DeleteMapping("/visualizacaovideo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAllVisualizacaoVideo() {
        return visualizacaoVideoService.deleteAll();
    }
}
