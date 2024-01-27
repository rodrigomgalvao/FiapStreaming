package com.bezkoder.spring.r2dbc.h2.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.bezkoder.spring.r2dbc.h2.model.Usuario;
import com.bezkoder.spring.r2dbc.h2.model.Video;

public interface VideoRepository extends R2dbcRepository<Video, Integer>{

}
