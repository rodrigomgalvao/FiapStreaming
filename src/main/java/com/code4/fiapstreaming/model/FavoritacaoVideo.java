package com.code4.fiapstreaming.model;

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
    private LocalDate dataFavoritacao =  LocalDate.now();
    private int idUsuario;
    private int idVideo;

}