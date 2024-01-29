package com.code4.fiapstreaming.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class VisualizacaoVideo {
    @Id
    private UUID id;
    private UUID idUsuario;
    private UUID idVideo;
    private LocalDate dataVisualizacao = LocalDate.now();
}
