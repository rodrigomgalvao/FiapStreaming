package com.code4.fiapstreaming.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code4.fiapstreaming.model.FavoritacaoVideo;
import com.code4.fiapstreaming.service.FavoritacaoVideoService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class FavoritacaoVideoController {
	@Autowired
	FavoritacaoVideoService favoritacaoVideoService;

	@PostMapping("/marcarComoFavorito")
	public Mono<FavoritacaoVideo> marcarComoFavorito(@RequestBody Map<String, Integer> requestBody) {
		Integer idUsuario = requestBody.get("idUsuario");
		Integer idVideo = requestBody.get("idVideo");

		if (idUsuario != null && idVideo != null) {
			return favoritacaoVideoService.marcarComoFavorito(idUsuario, idVideo);
		} else {
			return Mono.error(new IllegalArgumentException("idUsuario e/ou idVideo não podem ser nulos."));
		}
	}

	@PostMapping("/desmarcarComoFavorito")
	public Mono<String> desmarcarComoFavorito(@RequestBody Map<String, Integer> requestBody) {
		Integer idUsuario = requestBody.get("idUsuario");
		Integer idVideo = requestBody.get("idVideo");

		if (idUsuario != null && idVideo != null) {
			return favoritacaoVideoService.desmarcarComoFavorito(idUsuario, idVideo)
					.then(Mono.just("Operação de desmarcar favorito realizada com sucesso."));
		} else {
			return Mono.error(new IllegalArgumentException("idUsuario e/ou idVideo não podem ser nulos."));
		}
	}

}