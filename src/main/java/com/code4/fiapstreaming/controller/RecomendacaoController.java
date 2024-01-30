package com.code4.fiapstreaming.controller;



import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code4.fiapstreaming.model.Video;
import com.code4.fiapstreaming.service.RecomendacaoService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RecomendacaoController {

    private final RecomendacaoService recomendacaoService;

 // Endpoint para obter recomendações de vídeos com base nos favoritos do usuário
    @GetMapping("/usuarios/{idUsuario}/recomendacoes")
    public Flux<Video> obterRecomendacoes(@PathVariable UUID idUsuario) {
        return recomendacaoService.recomendarVideosComBaseNosFavoritos(idUsuario);
    }
}