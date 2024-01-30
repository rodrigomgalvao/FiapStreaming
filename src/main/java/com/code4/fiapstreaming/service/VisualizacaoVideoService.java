package com.code4.fiapstreaming.service;

import com.code4.fiapstreaming.repository.VisualizacaoVideoRepository;
import com.code4.fiapstreaming.model.VisualizacaoVideo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Service
public class VisualizacaoVideoService {
	@Autowired
	VisualizacaoVideoRepository visualizacaoVideoRepository;

	public Flux<VisualizacaoVideo> findAll() {
		return visualizacaoVideoRepository.findAll();
	}

	public Mono<VisualizacaoVideo> findById(UUID id) {
		return visualizacaoVideoRepository.findById(id);
	}

	public Mono<VisualizacaoVideo> save(VisualizacaoVideo visualizacaoVideo) {
		return visualizacaoVideoRepository.save(visualizacaoVideo);
	}

	public Mono<VisualizacaoVideo> update(UUID id, VisualizacaoVideo visualizacaoVideo) {
		return visualizacaoVideoRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
				.flatMap(optionalVideoCategoria -> {
					if (optionalVideoCategoria.isPresent()) {
						visualizacaoVideo.setId(id);
						return visualizacaoVideoRepository.save(visualizacaoVideo);
					}

					return Mono.empty();
				});
	}

	public Mono<Void> deleteById(UUID id) {
		return visualizacaoVideoRepository.deleteById(id);
	}

	public Mono<Double> calculateMediaVisualizacoes() {
		return visualizacaoVideoRepository.findAll().map(VisualizacaoVideo::getId).collectList().map(ids -> {
			double totalVisualizacoes = ids.size();
			if (totalVisualizacoes == 0) {
				return 0.0;
			} else {
				//contador de media de Visualizações
				return totalVisualizacoes / totalVisualizacoes;
			}
		});
	}
}
