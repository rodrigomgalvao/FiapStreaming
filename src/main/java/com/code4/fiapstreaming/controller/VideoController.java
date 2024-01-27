package com.code4.fiapstreaming.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.code4.fiapstreaming.model.Video;
import com.code4.fiapstreaming.service.VideoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
public class VideoController {
    @Autowired
    VideoService videoService;


    @GetMapping("/video/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Video> getVideoById(@PathVariable("id") int id) {
        return videoService.findById(id);

    }
    
    @GetMapping("/videosFilter")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Object> getVideos(@RequestParam(required = false) String titulo,
                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataPublicacao,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "3") int size) {
        if (titulo == null && dataPublicacao == null) {
            return Flux.just("Por favor, forneça critérios de busca (titulo e/ou dataPublicacao).");
        }
        
        Pageable pageable = PageRequest.of(page, size);
        Flux<Video> videos = videoService.findByTituloAndDataPublicacaoBeforeOrderByDataPublicacaoDesc(titulo, dataPublicacao, pageable);
        
        return videos
                .map(video -> (Object) video)
                .defaultIfEmpty("Nenhum vídeo encontrado com os critérios de busca.");
    }

 
    
    
    @GetMapping("/videos")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Video> getVideos(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return videoService.findAll(pageable);
    }

    @PostMapping("/video")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Video> createVideo(@RequestBody Video Video) {
        return videoService.save(Video);
    }


    @PutMapping("/video/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Video> updateVideo(@PathVariable("id") int id, @RequestBody Video Video) {
        return videoService.update(id, Video);
    }

    @DeleteMapping("/video/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteVideo(@PathVariable("id") int id) {
        return videoService.deleteById(id);
    }

    @DeleteMapping("/video")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAllVideos() {
        return videoService.deleteAll();
    }


}
