package com.bezkoder.spring.r2dbc.h2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisualizacaoVideo {
    @Id
    private int id;
    private int idUsuario;
    private int idVideo;
    private LocalTime dataHoraVisualizacao;
}
