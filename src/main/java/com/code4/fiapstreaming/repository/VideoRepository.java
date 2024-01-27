package com.code4.fiapstreaming.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.code4.fiapstreaming.model.Video;

import reactor.core.publisher.Flux;

public interface VideoRepository extends R2dbcRepository<Video, Integer>{


    @Query("SELECT * FROM video ORDER BY data_publicacao DESC")
    Flux<Video> findAllPagedOrderByDataPublicacaoDesc(Pageable pageable);
    


    @Query("SELECT * FROM video WHERE titulo_video LIKE :titulo AND data_publicacao <= :dataPublicacao ORDER BY data_publicacao DESC")
    Flux<Video> findByTituloAndDataPublicacaoBeforeOrderByDataPublicacaoDesc(String titulo, LocalDate dataPublicacao, Pageable pageable);
}


