package com.code4.fiapstreaming.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.code4.fiapstreaming.model.Video;
import com.code4.fiapstreaming.repository.VideoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VideoService {

  @Autowired
  VideoRepository videoRepository;

//  public Flux<Video> findByTituloAndDataPublicacaoBeforeOrderByDataPublicacaoDesc(String titulo, LocalDate dataPublicacao, Pageable pageable) {
//      return videoRepository.findByTituloAndDataPublicacaoBeforeOrderByDataPublicacaoDesc(titulo, dataPublicacao, pageable);
//  }
//  
  
  public Flux<Video> findByTituloAndDataPublicacaoBeforeOrderByDataPublicacaoDesc(String titulo, LocalDate dataPublicacao) {
	    return videoRepository.findByTituloAndDataPublicacaoBeforeOrderByDataPublicacaoDesc(titulo, dataPublicacao);
	}

  public Flux<Video> findAll(Pageable pageable) {
      return videoRepository.findAllByOrderByDataPublicacaoDesc(pageable);
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
