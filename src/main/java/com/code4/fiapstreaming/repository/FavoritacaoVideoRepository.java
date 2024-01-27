package com.code4.fiapstreaming.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.code4.fiapstreaming.model.FavoritacaoVideo;

public interface FavoritacaoVideoRepository extends R2dbcRepository<FavoritacaoVideo, Integer>{

}