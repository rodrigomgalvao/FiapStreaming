package com.bezkoder.spring.r2dbc.h2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bezkoder.spring.r2dbc.h2.model.Video;
import com.bezkoder.spring.r2dbc.h2.service.VideoService;
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
