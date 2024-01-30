package com.code4.fiapstreaming.service;



import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code4.fiapstreaming.model.Video;
import com.code4.fiapstreaming.repository.FavoritacaoVideoRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;


@Service
@RequiredArgsConstructor
public class RecomendacaoService {

	@Autowired
	private FavoritacaoVideoRepository favoritacaoVideoRepository;

	@Autowired
	private VideoService videoService;

// Método reativo para recomendar vídeos com base nos favoritos do usuário
	public Flux<Video> recomendarVideosComBaseNosFavoritos(UUID idUsuario) {
		return favoritacaoVideoRepository.findVideoIdsByUserId(idUsuario).flatMap(videoService::findById);
	}
}