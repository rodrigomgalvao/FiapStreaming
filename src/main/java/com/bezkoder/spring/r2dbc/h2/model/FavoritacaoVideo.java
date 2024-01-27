package com.bezkoder.spring.r2dbc.h2.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoritacaoVideo {


    @Id
    private int id;
    private LocalDate dataPublicacao;
    private int idUsuario;
    private int idVideo;

}