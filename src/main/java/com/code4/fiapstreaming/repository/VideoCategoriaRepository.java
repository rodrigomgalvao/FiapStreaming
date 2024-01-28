package com.code4.fiapstreaming.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.code4.fiapstreaming.model.VideoCategoria;

import java.util.UUID;

@Repository
public interface VideoCategoriaRepository extends R2dbcRepository<VideoCategoria, UUID>{
 // Flux<Tutorial> findByTitleContaining(String title);
  
  //Flux<Tutorial> findByPublished(boolean isPublished);
}
