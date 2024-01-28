package com.code4.fiapstreaming.model;

import java.time.LocalDate;
import java.util.UUID;

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
    private UUID id;
    private UUID idUsuario;
    private UUID idVideo;
    private LocalDate dataFavoritacao =  LocalDate.now();

}