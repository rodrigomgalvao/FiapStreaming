package com.code4.fiapstreaming.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.code4.fiapstreaming.model.FavoritacaoVideo;
import com.code4.fiapstreaming.service.FavoritacaoVideoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class FavoritacaoVideoController {
	@Autowired
	FavoritacaoVideoService favoritacaoVideoService;

	@GetMapping("/favoritos/{id}")
	public ResponseEntity<Mono<FavoritacaoVideo>> getFavoritacaoById(@PathVariable UUID id) {
		return ResponseEntity.ok(favoritacaoVideoService.findById(id));
	}
	@GetMapping("/favoritos")
	public ResponseEntity<Flux<FavoritacaoVideo>> getAllFavoritacoes() {
		return ResponseEntity.ok(favoritacaoVideoService.findAll());
	}
	@PostMapping("/favoritos")
	public ResponseEntity<Mono<FavoritacaoVideo>> createFavoritacao(@RequestBody FavoritacaoVideo favoritacaoVideo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(favoritacaoVideoService.save(favoritacaoVideo));
	}
	@PutMapping("/favoritos/{id}")
	public ResponseEntity<Mono<FavoritacaoVideo>> updateFavoritacao(
			@PathVariable UUID id, @RequestBody FavoritacaoVideo favoritacaoVideo) {
		return ResponseEntity.ok(favoritacaoVideoService.update(id, favoritacaoVideo));
	}
	@DeleteMapping("/favoritos/{id}")
	public ResponseEntity<Void> deleteFavoritacao(@PathVariable UUID id) {
		favoritacaoVideoService.deleteById(id).subscribe();
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/marcarComoFavorito")
	public Mono<FavoritacaoVideo> marcarComoFavorito(@RequestBody Map<String, UUID> requestBody) {
		UUID idUsuario = requestBody.get("idUsuario");
		UUID idVideo = requestBody.get("idVideo");

		if (idUsuario != null && idVideo != null) {
			return favoritacaoVideoService.marcarComoFavorito(idUsuario, idVideo);
		} else {
			return Mono.error(new IllegalArgumentException("idUsuario e/ou idVideo não podem ser nulos."));
		}
	}

	@PostMapping("/desmarcarComoFavorito")
	public Mono<String> desmarcarComoFavorito(@RequestBody Map<String, UUID> requestBody) {
		UUID idUsuario = requestBody.get("idUsuario");
		UUID idVideo = requestBody.get("idVideo");

		if (idUsuario != null && idVideo != null) {
			return favoritacaoVideoService.desmarcarComoFavorito(idUsuario, idVideo)
					.then(Mono.just("Operação de desmarcar favorito realizada com sucesso."));
		} else {
			return Mono.error(new IllegalArgumentException("idUsuario e/ou idVideo não podem ser nulos."));
		}
	}
	
    @GetMapping("/listaDeFavoritos")
    @ResponseStatus(HttpStatus.OK)
    public Flux<FavoritacaoVideo> getVideoCategoriaById() {
        return favoritacaoVideoService.findAll();
    }

}