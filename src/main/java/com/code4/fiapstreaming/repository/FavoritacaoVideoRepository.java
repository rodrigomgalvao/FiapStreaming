package com.code4.fiapstreaming.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;

import com.code4.fiapstreaming.model.FavoritacaoVideo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FavoritacaoVideoRepository extends R2dbcRepository<FavoritacaoVideo, UUID>{
	 
	 @Query("DELETE FROM favoritacao_video WHERE id_usuario = :idUsuario AND id_video = :idVideo")
	    Mono<Void> deleteByUsuarioIdAndVideoId(UUID idUsuario, UUID idVideo);
	 
	 
	 @Query("SELECT id_video FROM favoritacao_video WHERE id_usuario = :idUsuario")
	 Flux<UUID> findVideoIdsByUserId(@Param("idUsuario") UUID idUsuario);
	 
	 


}

