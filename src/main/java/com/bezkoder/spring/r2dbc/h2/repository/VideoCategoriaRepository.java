package com.bezkoder.spring.r2dbc.h2.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.r2dbc.h2.model.VideoCategoria;

@Repository
public interface VideoCategoriaRepository extends R2dbcRepository<VideoCategoria, Integer>{
 // Flux<Tutorial> findByTitleContaining(String title);
  
  //Flux<Tutorial> findByPublished(boolean isPublished);
}
