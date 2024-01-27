package com.code4.fiapstreaming.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.code4.fiapstreaming.model.Usuario;
import com.code4.fiapstreaming.model.Video;

public interface VideoRepository extends R2dbcRepository<Video, Integer>{

}
