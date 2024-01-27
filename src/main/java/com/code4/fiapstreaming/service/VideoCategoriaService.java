package com.code4.fiapstreaming.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code4.fiapstreaming.model.VideoCategoria;
import com.code4.fiapstreaming.repository.VideoCategoriaRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VideoCategoriaService {

  @Autowired
  VideoCategoriaRepository videoCategoriaRepository;

  public Flux<VideoCategoria> findAll() {
    return videoCategoriaRepository.findAll();
  }



  public Mono<VideoCategoria> findById(int id){
    return videoCategoriaRepository.findById(id);
  }

  public Mono<VideoCategoria> save(VideoCategoria videoCategoria) {
    return videoCategoriaRepository.save(videoCategoria);
  }

  public Mono<VideoCategoria> update(int id, VideoCategoria videoCategoria) {
    return videoCategoriaRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
        .flatMap(optionalVideoCategoria -> {
          if (optionalVideoCategoria.isPresent()) {
        	  videoCategoria.setId(id);
            return videoCategoriaRepository.save(videoCategoria);
          }

          return Mono.empty();
        });
  }

  public Mono<Void> deleteById(int id) {
    return videoCategoriaRepository.deleteById(id);
  }

  public Mono<Void> deleteAll() {
    return videoCategoriaRepository.deleteAll();
  }


}
