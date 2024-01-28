package com.code4.fiapstreaming.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.code4.fiapstreaming.model.VisualizacaoVideo;

import java.util.UUID;

public interface VisualizacaoVideoRepository extends R2dbcRepository<VisualizacaoVideo, UUID> {
}
