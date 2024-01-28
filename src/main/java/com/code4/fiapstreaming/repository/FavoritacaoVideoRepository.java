package com.code4.fiapstreaming.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.code4.fiapstreaming.model.FavoritacaoVideo;

import reactor.core.publisher.Mono;

public interface FavoritacaoVideoRepository extends R2dbcRepository<FavoritacaoVideo, Integer>{


	 
	 
	 @Query("DELETE FROM favoritacao_video WHERE id_usuario = :idUsuario AND id_video = :idVideo")
	    Mono<Void> deleteByUsuarioIdAndVideoId(int idUsuario, int idVideo);
}

