package com.code4.fiapstreaming.controller;

import java.time.LocalDate;
import java.util.UUID;

import com.code4.fiapstreaming.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.code4.fiapstreaming.model.Video;
import com.code4.fiapstreaming.service.VideoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class VideoController {
	@Autowired
	VideoService videoService;

	@GetMapping("/videos/{id}")
	public ResponseEntity<Mono<Video>> getVideoById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(videoService.findById(id));

	}
	@GetMapping("/videosFilter")
	@ResponseStatus(HttpStatus.OK)
	public Flux<Object> getVideos(@RequestParam(required = false) String titulo,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataPublicacao) {
		if (titulo == null && dataPublicacao == null) {
			return Flux.just("Por favor, forneça critérios de busca (titulo e/ou dataPublicacao).");
		}

		Flux<Video> videos = videoService.findByTituloAndDataPublicacaoBeforeOrderByDataPublicacaoDesc(titulo,
				dataPublicacao);

		return videos.map(video -> (Object) video)
				.defaultIfEmpty("Nenhum vídeo encontrado com os critérios de busca.");
	}
	@GetMapping("/videos")
	public ResponseEntity<Flux<Video>> getAllVideos(@RequestParam(defaultValue = "0") int page,
	                             @RequestParam(defaultValue = "3") int size) {

	    // Configura a ordenação por data de publicação, decrescente
	    Pageable pageable = PageRequest.of(page, size, Sort.by("dataPublicacao").descending());

	    // Chama o serviço para recuperar os vídeos
	    return ResponseEntity.ok(videoService.findAll(pageable));
	}
	@GetMapping("/videos/categoria/{idCategoria}")
	public ResponseEntity<Flux<Video>> getVideosByCategory(@PathVariable UUID idCategoria) {
		return ResponseEntity.ok(videoService.findByIdCategory(idCategoria));
	}
	@PostMapping("/videos")
	public ResponseEntity<Mono<Video>> createVideo(@RequestBody Video Video) {
		return ResponseEntity.status(HttpStatus.CREATED).body(videoService.save(Video));
	}
	@PutMapping("/videos/{id}")
	public ResponseEntity<Mono<Video>> updateVideo(@PathVariable("id") UUID id, @RequestBody Video Video) {
		return ResponseEntity.ok(videoService.update(id, Video));
	}
	@DeleteMapping("/videos/{id}")
	public ResponseEntity<Void> deleteVideo(@PathVariable UUID id) {
		videoService.deleteById(id).subscribe();
		return ResponseEntity.noContent().build();
	}

}
