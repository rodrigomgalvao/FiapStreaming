package com.bezkoder.spring.r2dbc.h2.repository;

import com.bezkoder.spring.r2dbc.h2.model.VisualizacaoVideo;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface VisualizacaoVideoRepository extends R2dbcRepository<VisualizacaoVideo, Integer> {
}
