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
public class Video {
	
	@Id
	private UUID id;
	private String tituloVideo;
	private String descricao;
	private String url;
	private LocalDate dataPublicacao = LocalDate.now();
	private UUID idCategoria;

}
