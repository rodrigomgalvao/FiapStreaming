package com.code4.fiapstreaming.controller;

import com.code4.fiapstreaming.model.Usuario;
import com.code4.fiapstreaming.model.VisualizacaoVideo;
import com.code4.fiapstreaming.service.VisualizacaoVideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class VisualizacaoVideoController {
    @Autowired
    VisualizacaoVideoService visualizacaoVideoService;

    @GetMapping("/visualizacaovideos/{id}")
    public ResponseEntity<Mono<VisualizacaoVideo>> getVisualizacaoVideoById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(visualizacaoVideoService.findById(id));
    }
    @GetMapping("/visualizacaovideos")
    public ResponseEntity<Flux<VisualizacaoVideo>> getAllVisualizacaoVideos() {
        return ResponseEntity.ok(visualizacaoVideoService.findAll());
    }

    
    @PostMapping("/visualizacaovideos")
    public ResponseEntity<Mono<VisualizacaoVideo>> createVisualizacaoVideo1(@RequestBody VisualizacaoVideo visualizacaoVideo) {
        // Directly return the Mono from the service
        return ResponseEntity.status(HttpStatus.CREATED)
                            .body(visualizacaoVideoService.save(visualizacaoVideo));
    }

    
    @PutMapping("/visualizacaovideos/{id}")
    public ResponseEntity<Mono<VisualizacaoVideo>> updateVisualizacaoVideo(
            @PathVariable("id") UUID id, @RequestBody VisualizacaoVideo visualizacaoVideo) {
        return ResponseEntity.ok(visualizacaoVideoService.update(id, visualizacaoVideo));
    }
    @DeleteMapping("/visualizacaovideos/{id}")
    public ResponseEntity<Void> deleteVisualizacaoVideo(@PathVariable UUID id) {
        visualizacaoVideoService.deleteById(id).subscribe();
        return ResponseEntity.noContent().build();
    }

}
