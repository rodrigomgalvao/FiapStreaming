package com.code4.fiapstreaming.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.code4.fiapstreaming.model.FavoritacaoVideo;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface FavoritacaoVideoRepository extends R2dbcRepository<FavoritacaoVideo, UUID>{
	 
	 @Query("DELETE FROM favoritacao_video WHERE id_usuario = :idUsuario AND id_video = :idVideo")
	    Mono<Void> deleteByUsuarioIdAndVideoId(UUID idUsuario, UUID idVideo);
}

