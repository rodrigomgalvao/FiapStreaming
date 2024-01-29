package com.code4.fiapstreaming.controller;

import com.code4.fiapstreaming.service.VideoCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code4.fiapstreaming.model.VideoCategoria;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
@RequestMapping("/api")
public class VideoCategoriaController {
    @Autowired
    VideoCategoriaService videoCategoriaService;
    @GetMapping("/videocategorias/{id}")
    public ResponseEntity<Mono<VideoCategoria>> getVideoCategoriaById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(videoCategoriaService.findById(id));
    }
    @GetMapping("/videocategorias")
    public ResponseEntity<Flux<VideoCategoria>> getAllVideoCategorias() {
        return ResponseEntity.ok(videoCategoriaService.findAll());
    }
    @PostMapping("/videocategorias")
    public ResponseEntity<Mono<VideoCategoria>> createVideoCategoria(@RequestBody VideoCategoria videoCategoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(videoCategoriaService.save(videoCategoria));
    }
    @PutMapping("/videocategorias/{id}")
    public ResponseEntity<Mono<VideoCategoria>> updateVideoCategoria
            (@PathVariable("id") UUID id, @RequestBody VideoCategoria videoCategoria) {
        return ResponseEntity.ok(videoCategoriaService.update(id, videoCategoria));
    }
    @DeleteMapping("/videocategorias/{id}")
    public ResponseEntity<Void> deleteVideoCategoria(@PathVariable UUID id) {
        videoCategoriaService.deleteById(id).subscribe();
        return ResponseEntity.noContent().build();
    }

}
