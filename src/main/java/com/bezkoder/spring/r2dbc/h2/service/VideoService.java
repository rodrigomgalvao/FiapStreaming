package com.bezkoder.spring.r2dbc.h2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.r2dbc.h2.model.Video;
import com.bezkoder.spring.r2dbc.h2.repository.VideoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VideoService {

  @Autowired
  VideoRepository videoRepository;

  public Flux<Video> findAll() {
    return videoRepository.findAll();
  }



  public Mono<Video> findById(int id){
    return videoRepository.findById(id);
  }

  public Mono<Video> save(Video video) {
    return videoRepository.save(video);
  }

  public Mono<Video> update(int id, Video video) {
    return videoRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
        .flatMap(optionalVideoCategoria -> {
          if (optionalVideoCategoria.isPresent()) {
        	  video.setId(id);
            return videoRepository.save(video);
          }

          return Mono.empty();
        });
  }

  public Mono<Void> deleteById(int id) {
    return videoRepository.deleteById(id);
  }

  public Mono<Void> deleteAll() {
    return videoRepository.deleteAll();
  }


}
