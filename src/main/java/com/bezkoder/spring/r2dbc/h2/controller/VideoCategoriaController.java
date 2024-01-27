package com.bezkoder.spring.r2dbc.h2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bezkoder.spring.r2dbc.h2.model.VideoCategoria;
import com.bezkoder.spring.r2dbc.h2.service.VideoCategoriaService;

import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
public class VideoCategoriaController {
  @Autowired
  VideoCategoriaService videoCategoriaService;
  


  
  @GetMapping("/videoCategorias/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<VideoCategoria> getVideoCategoriaById(@PathVariable("id") int id) {
	   return videoCategoriaService.findById(id);

  }

  @PostMapping("/videoCategorias")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<VideoCategoria> createVideoCategoria(@RequestBody VideoCategoria videoCategoria) {
    return videoCategoriaService.save(videoCategoria);
  }
  


  @PutMapping("/videoCategorias/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<VideoCategoria> updateVideoCategoria(@PathVariable("id") int id, @RequestBody VideoCategoria videoCategoria) {
	  return videoCategoriaService.update(id, videoCategoria);
  }

  @DeleteMapping("/videoCategorias/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteVideoCategoria(@PathVariable("id") int id) {
    return videoCategoriaService.deleteById(id);
  }

  @DeleteMapping("/videoCategorias")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteAllVideoCategorias() {
    return videoCategoriaService.deleteAll();
  }


}
